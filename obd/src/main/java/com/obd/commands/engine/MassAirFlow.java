package com.obd.commands.engine;

import android.annotation.SuppressLint;

import com.github.pires.obd.commands.engine.MassAirFlowCommand;
import com.obd.commands.Command;

public class MassAirFlow extends MassAirFlowCommand {
    protected float value;

    public MassAirFlow() {
        super();

        value = -1.0f;
    }

    @Override
    protected void performCalculations() {
        if (Command.isEightBit()) {
            value = (buffer.get(2) * 256 + buffer.get(3)) / 100.0f;
        } else {
            value = (buffer.get(2) + buffer.get(3)) / 100.0f;
        }
    }

    @SuppressLint("DefaultLocale")
    @Override
    public String getFormattedResult() {
        return String.format("%.2f%s", value, getResultUnit());
    }

    @Override
    public String getCalculatedResult() {
        return String.valueOf(value);
    }

    @Override
    public String getResultUnit() {
        return "g/s";
    }

    @Override
    public double getMAF() {
        return value;
    }
}
