package com.obd.command.temperature;

import com.obd.command.Command;
import com.obd.command.CommandListener;
import com.obd.eltonvs.temperature.EltonvsAAT;
import com.obd.pires.temperature.PiresAAT;

public class TemperatureAAT extends Command {
    public TemperatureAAT(CommandListener listener) {
        super(listener);
    }

    @Override
    protected void run(CommandListener listener) {
        if (commandType.equals(ELTONVS)) {
            new EltonvsAAT(listener);
        } else if (commandType.equals(PIRES)) {
            new PiresAAT(listener);
        }
    }

    @Override
    protected String getUnit() {
        return "°C";
    }
}
