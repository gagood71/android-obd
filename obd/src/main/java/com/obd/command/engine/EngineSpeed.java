package com.obd.command.engine;

import com.obd.command.Command;
import com.obd.command.CommandListener;
import com.obd.eltonvs.engine.EltonvsSpeed;
import com.obd.pires.engine.PiresSpeed;

public class EngineSpeed extends Command {
    public EngineSpeed(CommandListener listener) {
        super(listener);
    }

    @Override
    protected void run(CommandListener listener) {
        if (commandType.equals(ELTONVS)) {
            new EltonvsSpeed(listener);
        } else if (commandType.equals(PIRES)) {
            new PiresSpeed(listener);
        }
    }

    @Override
    protected String getUnit() {
        return "Km/h";
    }
}
