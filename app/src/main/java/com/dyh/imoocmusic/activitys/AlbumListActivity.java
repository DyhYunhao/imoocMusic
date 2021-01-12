package com.dyh.imoocmusic.activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.dyh.imoocmusic.R;
import com.dyh.imoocmusic.adapters.MusicHotAdapter;

public class AlbumListActivity extends BaseActivity {

    private RecyclerView mRcvAlbumList;
    private MusicHotAdapter mAlbumAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_list);

        initView();
    }

    private void initView() {
        initNavBar(true, "专辑列表", false);

        mRcvAlbumList = findViewById(R.id.rcv_album_list);
        mRcvAlbumList.setLayoutManager(new LinearLayoutManager(this));
        mRcvAlbumList.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mAlbumAdapter = new MusicHotAdapter(this, null);
        mRcvAlbumList.setAdapter(mAlbumAdapter);
    }
}