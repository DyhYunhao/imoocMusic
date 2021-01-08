package com.dyh.imoocmusic.activitys

import android.os.Bundle
import android.os.strictmode.DiskReadViolation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dyh.imoocmusic.R
import com.dyh.imoocmusic.adapters.MusicGridAdapter
import com.dyh.imoocmusic.views.GridItemDecoration

class MainActivity : BaseActivity() {

    private lateinit var mRcvSuggestSing: RecyclerView
    private lateinit var mMusicGridAdapter: MusicGridAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        initNavBar(false, "D音乐", true)

        mRcvSuggestSing = findViewById(R.id.rcv_suggest_sing)
        mRcvSuggestSing.layoutManager = GridLayoutManager(this, 3)
        mRcvSuggestSing.addItemDecoration(
            GridItemDecoration(resources.getDimensionPixelSize(R.dimen.albumMarginSize), mRcvSuggestSing))
        mMusicGridAdapter = MusicGridAdapter(this, null)
        mRcvSuggestSing.adapter = mMusicGridAdapter
    }
}