package com.dyh.imoocmusic.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.dyh.imoocmusic.R;
import com.dyh.imoocmusic.activitys.AlbumListActivity;
import com.dyh.imoocmusic.activitys.PlayMusicActivity;
import com.dyh.imoocmusic.models.MusicModel;
import com.dyh.imoocmusic.models.SongModel;
import com.dyh.imoocmusic.utils.LoginUtil;

import java.util.List;

/**
 * describe: 最热歌单的适配器
 * create by daiyh on 2021-1-11
 */
public class MusicHotAdapter extends RecyclerView.Adapter<MusicHotAdapter.HotMusicHolder> {

    private Context mContext;
    private View mItemView;
    private RecyclerView mRcvHotMusic;
    private boolean flag;
    private List<SongModel> mData;

    public MusicHotAdapter(Context mContext, RecyclerView mRcvHotMusic, List<SongModel> mData) {
        this.mContext = mContext;
        this.mRcvHotMusic = mRcvHotMusic;
        this.mData = mData;
    }

    @NonNull
    @Override
    public HotMusicHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mItemView = LayoutInflater.from(mContext).inflate(R.layout.item_hot_music, parent, false);
        return new HotMusicHolder(mItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull HotMusicHolder holder, int position) {
        setHeight();

        SongModel songModel = mData.get(position);

        Glide.with(mContext)
                .load(songModel.getPoster())
                .into(holder.mIvHotMusic);

        holder.mTvMusicName.setText(songModel.getName());
        holder.mTvAuthor.setText(songModel.getAuthor());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, PlayMusicActivity.class);
                intent.putExtra(PlayMusicActivity.MUSIC_ID, songModel.getMusicId());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    /**
     * 1.获取itemView的高度
     * 2.item的数量
     * 3.乘积为recycleView高度
     */
    private void setHeight() {

        if (flag || mRcvHotMusic == null) {
            return;
        }
        flag = true;

        RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) mItemView.getLayoutParams();
        int itemCount = getItemCount();
        int recycleViewHeight = params.height * itemCount;
        //设置recycleView高度
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mRcvHotMusic.getLayoutParams();
        lp.height = recycleViewHeight;
        mRcvHotMusic.setLayoutParams(lp);
    }

    static class HotMusicHolder extends RecyclerView.ViewHolder {

        private ImageView mIvHotMusic;
        private TextView mTvMusicName, mTvAuthor;
        private View itemView;

        public HotMusicHolder(@NonNull View itemView) {
            super(itemView);

            mIvHotMusic = itemView.findViewById(R.id.iv_item_music_img);
            mTvMusicName = itemView.findViewById(R.id.tv_item_music_name);
            mTvAuthor = itemView.findViewById(R.id.tv_item_music_author);
            this.itemView = itemView;
        }
    }
}
