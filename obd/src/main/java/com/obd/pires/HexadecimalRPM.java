package com.obd.pires;

import android.annotation.SuppressLint;

import com.github.pires.obd.commands.engine.RPMCommand;

public class HexadecimalRPM extends RPMCommand {
    protected int rpm;

    public HexadecimalRPM() {
        super();

        rpm = 0;
    }

    @Override
    protected void performCalculations() {
        // 因為 ECU 信號是 16 位元。
        // 不需要乘上 256，例如：rpm = (buffer.get(2) * 256 + buffer.get(3)) / 4;
        rpm = (buffer.get(2) + buffer.get(3)) / 4;
    }

    @SuppressLint("DefaultLocale")
    @Override
    public String getFormattedResult() {
        return String.format("%d%s", rpm, getResultUnit());
    }

    @Override
    public String getCalculatedResult() {
        return String.valueOf(rpm);
    }

    @Override
    public int getRPM() {
        return rpm;
    }
}
