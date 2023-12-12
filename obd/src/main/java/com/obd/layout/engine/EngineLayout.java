package com.obd.layout.engine;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;

import com.core.activities.v1.CompatActivity;
import com.obd.R;
import com.obd.layout.DefaultLayout;

@SuppressLint("ViewConstructor")
public class EngineLayout extends DefaultLayout {
    CompatActivity<?> activity;

    LinearLayout contentLayout;

    ALLayout alLayout;
    MAFLayout mafLayout;
    RPMLayout rpmLayout;
    SpeedLayout speedLayout;
    TPLayout tpLayout;
    RTPLayout rtpLayout;

    public EngineLayout(CompatActivity<?> compatActivity) {
        super(compatActivity);

        activity = compatActivity;
    }

    @Override
    public void startCommand() {
        alLayout.startCommand();
        mafLayout.startCommand();
        rpmLayout.startCommand();
        speedLayout.startCommand();
        tpLayout.startCommand();
        rtpLayout.startCommand();
    }

    @Override
    protected void initView(View view) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                dp2px(50));

        new Thread(() -> {
            alLayout = new ALLayout(getContext());
            alLayout.setLayoutParams(params);

            mafLayout = new MAFLayout(getContext());
            mafLayout.setLayoutParams(params);

            rpmLayout = new RPMLayout(getContext());
            rpmLayout.setLayoutParams(params);

            speedLayout = new SpeedLayout(getContext());
            speedLayout.setLayoutParams(params);

            tpLayout = new TPLayout(getContext());
            tpLayout.setLayoutParams(params);

            rtpLayout = new RTPLayout(getContext());
            rtpLayout.setLayoutParams(params);

            new Handler(getContext().getMainLooper()).post(() -> {
                contentLayout = view.findViewById(R.id.content_layout);
                contentLayout.addView(alLayout);
                contentLayout.addView(mafLayout);
                contentLayout.addView(rpmLayout);
                contentLayout.addView(speedLayout);
                contentLayout.addView(tpLayout);
                contentLayout.addView(rtpLayout);
            });
        }).start();
    }

    @Override
    protected int getViewId() {
        return R.layout.layout_engine;
    }
}
