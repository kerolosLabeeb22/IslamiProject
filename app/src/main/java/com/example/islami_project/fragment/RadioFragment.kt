package com.example.islami_project.fragment

import android.os.*
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.islami_project.api.ApiManager
import com.example.islami_project.api.model.MutableRadiosItem
import com.example.islami_project.api.model.RadioResponse
import com.example.islami_project.databinding.FragmentRadioBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RadioFragment : Fragment() {

    private lateinit var binding: FragmentRadioBinding
    private lateinit var adapter: RadioCardAdapter
    private val radioList = mutableListOf<MutableRadiosItem>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRadioBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = RadioCardAdapter(radioList)
        binding.radioRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.radioRecyclerView.adapter = adapter
        fetchRadioStations()
    }

    private fun fetchRadioStations() {
        ApiManager.apiService.getRadio().enqueue(object : Callback<RadioResponse> {
            override fun onResponse(
                call: Call<RadioResponse?>,
                response: Response<RadioResponse?>
            ) {
                val list = response.body()?.radios
                if (response.isSuccessful && list != null) {
                    radioList.clear()
                    radioList.addAll(list.filterNotNull().map {
                        MutableRadiosItem(
                            id = it.id ?: 0,
                            name = it.name ?: "Unknown",
                            url = it.url ?: "",
                            isMuted = false
                        )
                    })
                    adapter.notifyDataSetChanged()
                } else {
                    Toast.makeText(requireContext(), "API error", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<RadioResponse?>, t: Throwable) {
                Toast.makeText(requireContext(), "Failed: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
