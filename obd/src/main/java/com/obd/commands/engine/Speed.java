package com.obd.commands.engine;

import com.github.pires.obd.commands.SpeedCommand;
import com.obd.commands.Command;

public class Speed extends SpeedCommand {
    protected int value;

    public Speed() {
        super();

        value = 0;
    }

    @Override
    protected void performCalculations() {
        if (Command.isEightBit()) {
            value = (buffer.get(2) << 8) | buffer.get(3);
        } else {
            value = buffer.get(2);
        }
    }

    @Override
    public int getMetricSpeed() {
        return value;
    }

    @Override
    public float getImperialUnit() {
        return value * 0.621371192F;
    }
}
