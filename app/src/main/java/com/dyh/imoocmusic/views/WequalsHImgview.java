package com.dyh.imoocmusic.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

/**
 * describe: 宽高相等的imageView
 * create by daiyh on 2021-1-8
 */
public class WequalsHImgview extends AppCompatImageView {

    public WequalsHImgview(Context context) {
        super(context);
    }

    public WequalsHImgview(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public WequalsHImgview(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);

        //获取View宽度
//        int width = MeasureSpec.getSize(widthMeasureSpec);
        //获取View模式
//        int mode = MeasureSpec.getMode(widthMeasureSpec);

    }
}
