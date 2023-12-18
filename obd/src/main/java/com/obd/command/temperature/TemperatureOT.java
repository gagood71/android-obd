package com.obd.command.temperature;

import com.obd.command.Command;
import com.obd.command.CommandListener;
import com.obd.eltonvs.temperature.EltonvsOTCommand;
import com.obd.pires.temperature.PiresOTCommand;

public class TemperatureOT extends Command {
    public TemperatureOT(CommandListener listener) {
        super(listener);
    }

    @Override
    protected void run(CommandListener listener) {
        if (commandType.equals(ELTONVS)) {
            new EltonvsOTCommand(listener);
        } else if (commandType.equals(PIRES)) {
            new PiresOTCommand(listener);
        }
    }

    @Override
    protected String getUnit() {
        return "°C";
    }
}
