package com.obd.command.temperature;

import com.obd.command.Command;
import com.obd.command.CommandListener;
import com.obd.eltonvs.temperature.EltonvsAT;
import com.obd.pires.temperature.PiresAT;

public class TemperatureAIT extends Command {
    public TemperatureAIT(CommandListener listener) {
        super(listener);
    }

    @Override
    protected void run(CommandListener listener) {
        if (commandType.equals(ELTONVS)) {
            new EltonvsAT(listener);
        } else if (commandType.equals(PIRES)) {
            new PiresAT(listener);
        }
    }

    @Override
    protected String getUnit() {
        return "°C";
    }
}
