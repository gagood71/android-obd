package com.obd.commands.engine;

import android.annotation.SuppressLint;

import com.github.pires.obd.commands.engine.RPMCommand;
import com.obd.commands.Command;

public class RPM extends RPMCommand {
    protected int value;

    public RPM() {
        super();

        value = 0;
    }

    @Override
    protected void performCalculations() {
        if (Command.isEightBit()) {
            value = (buffer.get(2) + buffer.get(3)) / 4;
        } else {
            value = (buffer.get(2) * 256 + buffer.get(3)) / 4;
        }
    }

    @SuppressLint("DefaultLocale")
    @Override
    public String getFormattedResult() {
        return String.format("%d%s", value, getResultUnit());
    }

    @Override
    public String getCalculatedResult() {
        return String.valueOf(value);
    }

    @Override
    public int getRPM() {
        return value;
    }
}
