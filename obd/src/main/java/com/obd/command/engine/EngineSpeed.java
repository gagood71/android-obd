package com.obd.command.engine;

import com.obd.command.Command;
import com.obd.command.CommandListener;
import com.obd.eltonvs.engine.EltonvsSpeedCommand;
import com.obd.pires.engine.PiresSpeedCommand;

public class EngineSpeed extends Command {
    public EngineSpeed(CommandListener listener) {
        super(listener);
    }

    @Override
    protected void run(CommandListener listener) {
        if (commandType.equals(ELTONVS)) {
            new EltonvsSpeedCommand(listener);
        } else if (commandType.equals(PIRES)) {
            new PiresSpeedCommand(listener);
        }
    }

    @Override
    protected String getUnit() {
        return "Km/h";
    }
}
