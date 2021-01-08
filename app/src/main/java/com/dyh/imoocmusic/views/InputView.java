package com.dyh.imoocmusic.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.dyh.imoocmusic.R;

/**
 * describe:自定义输入框
 * 1.输入框之前的图标 input_icon
 * 2.输入框提示的内容 input_hint
 * 3.输入的内容是否以密文展示 is_password
 * create by daiyh on 2021-1-5
 */
public class InputView extends FrameLayout {

    private int mInputIcon;
    private String mInputHint;
    private boolean mIsPassword;

    private View mView;
    private ImageView mIvIcon;
    private EditText mEtInput;

    public InputView(@NonNull Context context) {
        super(context);
        init(context, null);
    }

    public InputView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public InputView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public InputView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        if (attrs == null) return;
        //获取自定属性
        @SuppressLint({"Recycle", "CustomViewStyleable"})
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.inputView);
        mInputIcon = typedArray.getResourceId(R.styleable.inputView_input_icon, R.mipmap.logo);
        mInputHint = typedArray.getString(R.styleable.inputView_input_hint);
        mIsPassword = typedArray.getBoolean(R.styleable.inputView_is_password, false);
        typedArray.recycle();

        //绑定view
        mView = LayoutInflater.from(context).inflate(R.layout.style_input_view, this, false);
        mIvIcon = mView.findViewById(R.id.iv_input_icon);
        mEtInput = mView.findViewById(R.id.et_input_text);

        //布局关联属性
        mIvIcon.setImageResource(mInputIcon);
        mEtInput.setHint(mInputHint);
        mEtInput.setInputType(mIsPassword ?
                InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD :
                InputType.TYPE_CLASS_PHONE);

        addView(mView);
    }

    /**
     * 返回输入的内容
     */
    public String getInputStr() {
        return mEtInput.getText().toString().trim();
    }
}
