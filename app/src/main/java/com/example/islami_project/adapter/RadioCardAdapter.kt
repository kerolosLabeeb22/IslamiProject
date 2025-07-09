package com.example.islami_project.fragment

import android.media.MediaPlayer
import android.view.*
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.islami_project.R
import com.example.islami_project.api.model.MutableRadiosItem

class RadioCardAdapter(
    private val radios: List<MutableRadiosItem>
) : RecyclerView.Adapter<RadioCardAdapter.RadioViewHolder>() {

    private var currentPlayer: MediaPlayer? = null
    private var currentPlayingPosition: Int = -1

    inner class RadioViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val radioName: TextView = itemView.findViewById(R.id.radioName)
        val playPauseBtn: ImageButton = itemView.findViewById(R.id.btnPlayPause)
        val loadingBar: ProgressBar = itemView.findViewById(R.id.loadingWave)
        val volumeBtn: ImageButton = itemView.findViewById(R.id.btnVolume)
        val volumeSeekBar: SeekBar = itemView.findViewById(R.id.volume_seekbar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RadioViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.radio_card, parent, false)
        return RadioViewHolder(view)
    }

    override fun onBindViewHolder(holder: RadioViewHolder, position: Int) {
        val radio = radios[position]
        val isThisPlaying = radio.isPlaying
        val isThisPaused = radio.isPaused

        holder.radioName.text = radio.name

        holder.playPauseBtn.setImageResource(
            when {
                isThisPlaying -> R.drawable.ic_pause
                isThisPaused -> R.drawable.ic_play
                else -> R.drawable.ic_play
            }
        )

        holder.volumeSeekBar.visibility = if (isThisPlaying || isThisPaused) View.VISIBLE else View.GONE
        holder.volumeSeekBar.progress = radio.volumeLevel
        holder.volumeBtn.setImageResource(if (radio.isMuted || radio.volumeLevel == 0) R.drawable.ic_volume_off else R.drawable.ic_volume)

        holder.loadingBar.visibility = View.GONE

        // Avoid duplicate listeners
        holder.volumeSeekBar.setOnSeekBarChangeListener(null)

        holder.volumeSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (position == currentPlayingPosition && fromUser) {
                    val volume = progress / 100f
                    currentPlayer?.setVolume(volume, volume)

                    radio.volumeLevel = progress
                    radio.isMuted = progress == 0

                    holder.volumeBtn.setImageResource(
                        if (radio.isMuted) R.drawable.ic_volume_off else R.drawable.ic_volume
                    )
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        holder.playPauseBtn.setOnClickListener {
            if (radio.isPlaying) {
                currentPlayer?.pause()
                radio.isPlaying = false
                radio.isPaused = true
                notifyItemChanged(position)
                return@setOnClickListener
            }

            if (radio.isPaused) {
                currentPlayer?.start()
                radio.isPlaying = true
                radio.isPaused = false
                notifyItemChanged(position)
                return@setOnClickListener
            }

            // Stop previously playing
            if (currentPlayingPosition != -1) {
                val previous = radios[currentPlayingPosition]
                previous.isPlaying = false
                previous.isPaused = false
                currentPlayer?.stop()
                currentPlayer?.release()
                currentPlayer = null
                notifyItemChanged(currentPlayingPosition)
            }

            holder.loadingBar.visibility = View.VISIBLE
            holder.playPauseBtn.visibility = View.INVISIBLE

            currentPlayer = MediaPlayer().apply {
                setDataSource(radio.url)
                prepareAsync()

                setOnPreparedListener {
                    start()
                    currentPlayingPosition = position
                    radio.isPlaying = true
                    radio.isPaused = false

                    val volumeLevel = radio.volumeLevel.takeIf { it in 0..100 } ?: 100
                    val volume = if (radio.isMuted || volumeLevel == 0) 0f else volumeLevel / 100f
                    setVolume(volume, volume)

                    holder.loadingBar.visibility = View.GONE
                    holder.playPauseBtn.visibility = View.VISIBLE
                    holder.volumeSeekBar.visibility = View.VISIBLE
                    holder.volumeSeekBar.progress = radio.volumeLevel
                    holder.volumeBtn.setImageResource(
                        if (radio.isMuted || radio.volumeLevel == 0) R.drawable.ic_volume_off else R.drawable.ic_volume
                    )

                    notifyItemChanged(position)
                }

                setOnCompletionListener {
                    radio.isPlaying = false
                    radio.isPaused = false
                    notifyItemChanged(position)
                }
            }
        }

        holder.volumeBtn.setOnClickListener {
            radio.isMuted = !radio.isMuted
            if (radio.isMuted) {
                radio.volumeLevel = 0
            } else {
                if (radio.volumeLevel == 0) radio.volumeLevel = 100
            }

            if (position == currentPlayingPosition) {
                val volume = if (radio.isMuted) 0f else radio.volumeLevel / 100f
                currentPlayer?.setVolume(volume, volume)
            }

            holder.volumeBtn.setImageResource(
                if (radio.isMuted) R.drawable.ic_volume_off else R.drawable.ic_volume
            )
            holder.volumeSeekBar.progress = radio.volumeLevel
        }
    }

    override fun getItemCount(): Int = radios.size
}
