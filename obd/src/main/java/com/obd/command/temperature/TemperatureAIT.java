package com.obd.command.temperature;

import com.obd.command.Command;
import com.obd.command.CommandListener;
import com.obd.eltonvs.temperature.EltonvsATCommand;
import com.obd.pires.temperature.PiresATCommand;

public class TemperatureAIT extends Command {
    public TemperatureAIT(CommandListener listener) {
        super(listener);
    }

    @Override
    protected void run(CommandListener listener) {
        if (commandType.equals(ELTONVS)) {
            new EltonvsATCommand(listener);
        } else if (commandType.equals(PIRES)) {
            new PiresATCommand(listener);
        }
    }

    @Override
    protected String getUnit() {
        return "°C";
    }
}
