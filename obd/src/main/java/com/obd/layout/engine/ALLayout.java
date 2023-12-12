package com.obd.layout.engine;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.Nullable;

import com.obd.R;
import com.obd.command.CommandLayout;
import com.obd.command.CommandListener;
import com.obd.command.engine.EngineAL;

public class ALLayout extends CommandLayout {
    public ALLayout(Context context) {
        super(context);
    }

    public ALLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ALLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ALLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void startCommand() {
        new EngineAL(new CommandListener() {
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
        return getContext().getString(R.string.引擎_附載絕對值);
    }
}
