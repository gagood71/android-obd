package com.obd.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.core.activities.v1.CompatActivity;
import com.core.views.NormalText;
import com.core.views.layouts.linear.DefaultLinearLayout;

public abstract class DefaultLayout extends DefaultLinearLayout {
    protected CompatActivity<?> compatActivity;

    protected NormalText obdValue;
    protected NormalText obdUnit;

    public DefaultLayout(Context context) {
        super(context);
    }

    public DefaultLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DefaultLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public DefaultLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void init() {
        View view = View.inflate(getContext(), getViewId(), null);
        view.setLayoutParams(new LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT));

        removeAllViews();
        addView(view);
        initView(view);
    }

    public void setCompatActivity(CompatActivity<?> activity) {
        compatActivity = activity;
    }

    public abstract void startCommand();

    protected abstract void initView(View view);

    protected abstract int getViewId();
}
