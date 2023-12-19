package com.obd.pires;

import com.github.pires.obd.commands.SpeedCommand;

public class HexadecimalSpeed extends SpeedCommand {
    protected int speed;

    public HexadecimalSpeed() {
        super();

        speed = 0;
    }

    @Override
    protected void performCalculations() {
        speed = (buffer.get(2) << 8) | buffer.get(3);
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
