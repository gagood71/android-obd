package com.obd.command.temperature;

import com.obd.command.Command;
import com.obd.command.CommandListener;
import com.obd.eltonvs.temperature.EltonvsAATCommand;
import com.obd.pires.temperature.PiresAATCommand;

public class TemperatureAAT extends Command {
    public TemperatureAAT(CommandListener listener) {
        super(listener);
    }

    @Override
    protected void run(CommandListener listener) {
        if (commandType.equals(ELTONVS)) {
            new EltonvsAATCommand(listener);
        } else if (commandType.equals(PIRES)) {
            new PiresAATCommand(listener);
        }
    }

    @Override
    protected String getUnit() {
        return "°C";
    }
}
