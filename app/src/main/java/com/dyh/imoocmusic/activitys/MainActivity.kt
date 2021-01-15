package com.dyh.imoocmusic.activitys

import android.os.Bundle
import android.os.strictmode.DiskReadViolation
import android.view.animation.AnimationUtils
import android.widget.Adapter
import android.widget.ImageView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dyh.imoocmusic.R
import com.dyh.imoocmusic.adapters.MusicGridAdapter
import com.dyh.imoocmusic.adapters.MusicHotAdapter
import com.dyh.imoocmusic.models.MusicModel
import com.dyh.imoocmusic.utils.RealmUtil
import com.dyh.imoocmusic.views.GridItemDecoration

class MainActivity : BaseActivity() {

    private lateinit var mRcvSuggestSing: RecyclerView
    private lateinit var mRcvHotMusic: RecyclerView
    private lateinit var mMusicGridAdapter: MusicGridAdapter
    private lateinit var mHotMusicAdapter: MusicHotAdapter
    private lateinit var mRealmUtil: RealmUtil
    private lateinit var mMusicModel: MusicModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initData()
        initView()
    }

    private fun initData() {
        mRealmUtil = RealmUtil()
        mMusicModel = mRealmUtil.musicSource
//        mRealmUtil.close()
    }

    private fun initView() {
        initNavBar(false, "D音乐", true)

        mRcvSuggestSing = findViewById(R.id.rcv_suggest_sing)
        mRcvSuggestSing.layoutManager = GridLayoutManager(this, 3)
        mRcvSuggestSing.addItemDecoration(GridItemDecoration(resources.getDimensionPixelSize(R.dimen.albumMarginSize), mRcvSuggestSing))
        mRcvSuggestSing.isNestedScrollingEnabled = false
        mMusicGridAdapter = MusicGridAdapter(this, mMusicModel.album)
        mRcvSuggestSing.adapter = mMusicGridAdapter

        /**
         * 两个recycleView嵌套导致下面的高度计算出错，解决方式：
         * 1.已知列表高度的情况下，可以直接在布局中定义好高度
         * 2.不知道高度的情况下手动计算
         */
        mRcvHotMusic = findViewById(R.id.rcv_hot_music)
        mRcvHotMusic.layoutManager = LinearLayoutManager(this)
        mRcvHotMusic.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        mRcvHotMusic.isNestedScrollingEnabled = false
        mHotMusicAdapter = MusicHotAdapter(this, mRcvHotMusic, mMusicModel.hot)
        mRcvHotMusic.adapter = mHotMusicAdapter
    }

    override fun onDestroy() {
        super.onDestroy()
        mRealmUtil.close()
    }
}