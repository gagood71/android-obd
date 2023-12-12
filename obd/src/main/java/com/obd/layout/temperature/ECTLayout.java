package com.obd.layout.temperature;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.Nullable;

import com.obd.R;
import com.obd.command.CommandLayout;
import com.obd.command.CommandListener;
import com.obd.command.temperature.TemperatureECT;

public class ECTLayout extends CommandLayout {
    public ECTLayout(Context context) {
        super(context);
    }

    public ECTLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ECTLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ECTLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void startCommand() {
        new TemperatureECT(new CommandListener() {
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
        return getContext().getString(R.string.溫度_冷卻溫度);
    }
}
