package com.obd.command.temperature;

import com.obd.command.Command;
import com.obd.command.CommandListener;
import com.obd.eltonvs.temperature.EltonvsOT;
import com.obd.pires.temperature.PiresOT;

public class TemperatureOT extends Command {
    public TemperatureOT(CommandListener listener) {
        super(listener);
    }

    @Override
    protected void run(CommandListener listener) {
        if (commandType.equals(ELTONVS)) {
            new EltonvsOT(listener);
        } else if (commandType.equals(PIRES)) {
            new PiresOT(listener);
        }
    }

    @Override
    protected String getUnit() {
        return "°C";
    }
}
