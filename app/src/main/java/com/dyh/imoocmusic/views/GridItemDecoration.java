package com.dyh.imoocmusic.views;

import android.graphics.Rect;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * describe: 表格item之间的间隔
 * create by daiyh on 2021-1-8
 */
public class GridItemDecoration extends RecyclerView.ItemDecoration {
    private int mSpace;

    public GridItemDecoration(int mSpace, RecyclerView parent) {
        this.mSpace = mSpace;
        getOffset(parent);
    }

    /**
     * @param outRect Item 矩形的边界
     * @param view ItemView
     * @param parent RecyclerView
     * @param state RecyclerView的状态
     */
    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        outRect.left = mSpace;

        //判断是否是每一行的第一个item
//        if (parent.getChildLayoutPosition(view) % mRowCount == 0) {
//            outRect.left = 0;
//        }
    }

    private void getOffset(@NonNull RecyclerView parent) {
        //margin为正，则view会距离边界产生一个距离
        //margin为负，则view会超出边界产生一个距离
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) parent.getLayoutParams();
        params.leftMargin = -mSpace;
        parent.setLayoutParams(params);
    }
}
