package com.railway.rail.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.railway.common.utils.DisplayUtil;
import com.railway.rail.R;


/**
 * Created by fengpeihao on 16/6/3.
 */
public class BottomBarTab extends FrameLayout {
    private ImageView mIcon;
    private TextView mTvTitle;
    private Context mContext;
    private int mTabPosition = -1;

    private int[] iconsSelect = {R.mipmap.loan_select, R.mipmap.mall_select};
    private int[] icons = {R.mipmap.loan_normal, R.mipmap.mall_normal};

    public BottomBarTab(Context context, @DrawableRes int icon, CharSequence title) {
        this(context, null, icon, title);
    }

    public BottomBarTab(Context context, AttributeSet attrs, int icon, CharSequence title) {
        this(context, attrs, 0, icon, title);
    }

    public BottomBarTab(Context context, AttributeSet attrs, int defStyleAttr, int icon, CharSequence title) {
        super(context, attrs, defStyleAttr);
        init(context, icon, title);
    }

    private void init(Context context, int icon, CharSequence title) {

        mContext = context;
        TypedArray typedArray = context.obtainStyledAttributes(new int[]{R.attr.selectableItemBackgroundBorderless});
        Drawable drawable = typedArray.getDrawable(0);
        setBackgroundDrawable(drawable);
        typedArray.recycle();

        LinearLayout lLContainer = new LinearLayout(context);
        lLContainer.setOrientation(LinearLayout.VERTICAL);
        lLContainer.setGravity(Gravity.CENTER);
        LayoutParams paramsContainer = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        paramsContainer.gravity = Gravity.CENTER;
        lLContainer.setLayoutParams(paramsContainer);

        mIcon = new ImageView(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(DisplayUtil.dip2px(35), DisplayUtil.dip2px(35));
        mIcon.setImageResource(icon);
        mIcon.setLayoutParams(params);
        lLContainer.addView(mIcon);

        mTvTitle = new TextView(context);
        mTvTitle.setText(title);
        LinearLayout.LayoutParams paramsTv = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        mTvTitle.setTextSize(12);
        mTvTitle.setTextColor(ContextCompat.getColor(context, R.color.color_7c7a7a));
        mTvTitle.setLayoutParams(paramsTv);
        lLContainer.addView(mTvTitle);
        addView(lLContainer);
    }

    @Override
    public void setSelected(boolean selected) {
        super.setSelected(selected);
        if (selected) {
//                showAnimation(mIcon,animations[mTabPosition]);
            mIcon.setImageResource(iconsSelect[mTabPosition]);
            //mIcon.setColorFilter(ContextCompat.getColor(mContext, R.color.color_FB296B));
            mTvTitle.setTextColor(ContextCompat.getColor(mContext, R.color.color_252222));
        } else {
            //mIcon.setColorFilter(ContextCompat.getColor(mContext, R.color.color_A47282));
            mIcon.setImageResource(icons[mTabPosition]);
            mTvTitle.setTextColor(ContextCompat.getColor(mContext, R.color.color_7c7a7a));
        }
    }

    public void setTabPosition(int position, int currentPosition) {
        mTabPosition = position;
        if (position == currentPosition) {
            setSelected(true);
        }
    }

    public int getTabPosition() {
        return mTabPosition;
    }


    /**
     * 加载帧动画
     *
     * @param imageView
     * @param resourceId 帧动画
     */
    private void showAnimation(ImageView imageView, int resourceId) {
        imageView.setImageResource(resourceId);
        AnimationDrawable animationDrawable = (AnimationDrawable) imageView.getDrawable();
        //执行一次
        animationDrawable.setOneShot(true);
        animationDrawable.start();
    }
}
