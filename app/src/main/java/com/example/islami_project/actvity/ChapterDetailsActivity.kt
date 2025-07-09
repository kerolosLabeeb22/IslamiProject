package com.example.islami_project.actvity

import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.islami_project.adapter.VersesAdapter
import com.example.islami_project.constant.EXTRA
import com.example.islami_project.databinding.ActivityChapterDetailsBinding
import com.example.islami_project.api.model.Chapter

class ChapterDetailsActivity : AppCompatActivity() {
    lateinit var viewBinding: ActivityChapterDetailsBinding
    var chapter:Chapter?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        viewBinding = ActivityChapterDetailsBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        init()
        initRecyclerView()

    }
    lateinit var adapter: VersesAdapter
    private fun initRecyclerView() {
        adapter= VersesAdapter(versesList)
        viewBinding.content.versesRecycler.adapter=adapter
    }

    fun init(){
        setSupportActionBar(viewBinding.toolbar.toolbar)
       supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            chapter=intent.getParcelableExtra(
                EXTRA.EXTRA_CHAPTER,
                Chapter::class.java
            )
        }else{
            chapter=intent.getParcelableExtra(
                EXTRA.EXTRA_CHAPTER
            )
        }
        viewBinding.toolbar.toolbarTitle.text=chapter?.titleEn
        viewBinding.content.chapterTitleAr.text=chapter?.titleAr
        readChapterDetails(chapter!!.index)
    }

    lateinit var versesList : List<String>
    fun readChapterDetails(chapterIndex:Int){
       val content= assets.open("quran/${chapterIndex+1}.txt").bufferedReader().use{it.readText()}
        versesList= content.split("\n")
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}