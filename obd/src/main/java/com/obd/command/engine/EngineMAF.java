package com.obd.command.engine;

import com.obd.command.Command;
import com.obd.command.CommandListener;
import com.obd.eltonvs.engine.EltonvsMAFCommand;
import com.obd.pires.engine.PiresMAFCommand;

public class EngineMAF extends Command {
    public EngineMAF(CommandListener listener) {
        super(listener);
    }

    @Override
    protected void run(CommandListener listener) {
        if (commandType.equals(ELTONVS)) {
            new EltonvsMAFCommand(listener);
        } else if (commandType.equals(PIRES)) {
            new PiresMAFCommand(listener);
        }
    }

    @Override
    protected String getUnit() {
        return "g/s";
    }
}
