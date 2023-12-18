package com.obd.command.engine;

import com.obd.command.Command;
import com.obd.command.CommandListener;
import com.obd.eltonvs.engine.EltonvsALCommand;
import com.obd.pires.engine.PiresALCommand;

public class EngineAL extends Command {
    public EngineAL(CommandListener listener) {
        super(listener);
    }

    @Override
    protected void run(CommandListener listener) {
        if (commandType.equals(ELTONVS)) {
            new EltonvsALCommand(listener);
        } else if (commandType.equals(PIRES)) {
            new PiresALCommand(listener);
        }
    }

    @Override
    protected String getUnit() {
        return "%";
    }
}
