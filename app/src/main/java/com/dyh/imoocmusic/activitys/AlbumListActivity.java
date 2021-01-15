package com.dyh.imoocmusic.activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.dyh.imoocmusic.R;
import com.dyh.imoocmusic.adapters.MusicHotAdapter;
import com.dyh.imoocmusic.models.AlbumModel;
import com.dyh.imoocmusic.utils.RealmUtil;

public class AlbumListActivity extends BaseActivity {

    public static final String ALBUM_ID = "albumId";

    private RecyclerView mRcvAlbumList;
    private MusicHotAdapter mAlbumAdapter;
    private String mAlbumId;
    private RealmUtil mRealmUtil;
    private AlbumModel mAlbumModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_list);

        initData();
        initView();
    }

    private void initData() {
        mAlbumId = getIntent().getStringExtra(ALBUM_ID);
        mRealmUtil = new RealmUtil();
        mAlbumModel = mRealmUtil.getAlbum(mAlbumId);
    }

    private void initView() {
        initNavBar(true, "专辑列表", false);

        mRcvAlbumList = findViewById(R.id.rcv_album_list);
        mRcvAlbumList.setLayoutManager(new LinearLayoutManager(this));
        mRcvAlbumList.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mAlbumAdapter = new MusicHotAdapter(this, null, mAlbumModel.getList());
        mRcvAlbumList.setAdapter(mAlbumAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRealmUtil.close();
    }
}