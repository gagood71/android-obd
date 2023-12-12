package com.obd.layout.engine;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.Nullable;

import com.obd.R;
import com.obd.command.CommandLayout;
import com.obd.command.CommandListener;
import com.obd.command.engine.EngineRTP;

public class RTPLayout extends CommandLayout {
    public RTPLayout(Context context) {
        super(context);
    }

    public RTPLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RTPLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public RTPLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void startCommand() {
        new EngineRTP(new CommandListener() {
            @Override
            public void onSuccess(String value, String unit) {
                obdValueText.setText(value);
                obdUnitText.setText(unit);
            }

            @Override
            public void onFailed(String value, String unit) {
                obdValueText.setText(value);
                obdUnitText.setText(unit);
            }
        });
    }

    @Override
    protected String getCommandName() {
        return getContext().getString(R.string.引擎_相對油門位置);
    }
}
