package com.obd.command.temperature;

import com.obd.command.Command;
import com.obd.command.CommandListener;
import com.obd.eltonvs.temperature.EltonvsECTCommand;
import com.obd.pires.temperature.PiresECTCommand;

public class TemperatureECT extends Command {
    public TemperatureECT(CommandListener listener) {
        super(listener);
    }

    @Override
    protected void run(CommandListener listener) {
        if (commandType.equals(ELTONVS)) {
            new EltonvsECTCommand(listener);
        } else if (commandType.equals(PIRES)) {
            new PiresECTCommand(listener);
        }
    }

    @Override
    protected String getUnit() {
        return "°C";
    }
}
