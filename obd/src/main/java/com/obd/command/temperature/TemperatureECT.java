package com.obd.command.temperature;

import com.obd.command.Command;
import com.obd.command.CommandListener;
import com.obd.eltonvs.temperature.EltonvsECT;
import com.obd.pires.temperature.PiresECT;

public class TemperatureECT extends Command {
    public TemperatureECT(CommandListener listener) {
        super(listener);
    }

    @Override
    protected void run(CommandListener listener) {
        if (commandType.equals(ELTONVS)) {
            new EltonvsECT(listener);
        } else if (commandType.equals(PIRES)) {
            new PiresECT(listener);
        }
    }

    @Override
    protected String getUnit() {
        return "°C";
    }
}
