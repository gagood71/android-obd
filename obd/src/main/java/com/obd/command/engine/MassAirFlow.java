package com.obd.command.engine;

import android.annotation.SuppressLint;

import com.github.pires.obd.commands.engine.MassAirFlowCommand;
import com.obd.command.CommandCache;

public class MassAirFlow extends MassAirFlowCommand {
    protected float value = -1.0f;

    public MassAirFlow() {
        super();

        value = 0;
    }

    @Override
    protected void performCalculations() {
        if (CommandCache.ECU_BIT == CommandCache.ECU_16_BIT) {
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

    /** {@inheritDoc} */
    @Override
    public String getCalculatedResult() {
        return String.valueOf(value);
    }

    /** {@inheritDoc} */
    @Override
    public String getResultUnit() {
        return "g/s";
    }

    /**
     * <p>getMAF.</p>
     *
     * @return MAF value for further calculus.
     */
    public double getMAF() {
        return value;
    }
}
