package com.obd.layout.temperature;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;

import com.obd.R;
import com.core.activities.v1.CompatActivity;
import com.obd.layout.DefaultLayout;

@SuppressLint("ViewConstructor")
public class TemperatureLayout extends DefaultLayout {
    CompatActivity<?> activity;

    LinearLayout contentLayout;

    AATLayout aatLayout;
    AITLayout aitLayout;
    ECTLayout ectLayout;
    OTLayout otLayout;

    public TemperatureLayout(CompatActivity<?> compatActivity) {
        super(compatActivity);

        activity = compatActivity;
    }

    @Override
    public void startCommand() {
        aatLayout.startCommand();
        aitLayout.startCommand();
        ectLayout.startCommand();
        otLayout.startCommand();
    }

    @Override
    protected void initView(View view) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                dp2px(50));

        new Thread(() -> {
            aatLayout = new AATLayout(getContext());
            aatLayout.setLayoutParams(params);

            aitLayout = new AITLayout(getContext());
            aitLayout.setLayoutParams(params);

            ectLayout = new ECTLayout(getContext());
            ectLayout.setLayoutParams(params);

            otLayout = new OTLayout(getContext());
            otLayout.setLayoutParams(params);

            new Handler(getContext().getMainLooper()).post(() -> {
                contentLayout = view.findViewById(R.id.content_layout);
                contentLayout.addView(aatLayout);
                contentLayout.addView(aitLayout);
                contentLayout.addView(ectLayout);
                contentLayout.addView(otLayout);
            });
        }).start();
    }

    @Override
    protected int getViewId() {
        return R.layout.layout_temperature;
    }
}
