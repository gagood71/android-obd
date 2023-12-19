package com.obd.pires;

import com.github.pires.obd.commands.SpeedCommand;

public class OctalSpeed extends SpeedCommand {
    protected int speed;

    public OctalSpeed() {
        super();

        speed = 0;
    }

    @Override
    protected void performCalculations() {
        speed = buffer.get(2);
    }

    @Override
    public int getMetricSpeed() {
        return speed;
    }

    @Override
    public float getImperialUnit() {
        return speed * 0.621371192F;
    }
}
