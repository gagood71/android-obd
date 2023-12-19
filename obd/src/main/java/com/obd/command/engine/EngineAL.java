package com.obd.command.engine;

import com.obd.command.Command;
import com.obd.command.CommandListener;
import com.obd.eltonvs.engine.EltonvsAL;
import com.obd.pires.engine.PiresAL;

public class EngineAL extends Command {
    public EngineAL(CommandListener listener) {
        super(listener);
    }

    @Override
    protected void run(CommandListener listener) {
        if (commandType.equals(ELTONVS)) {
            new EltonvsAL(listener);
        } else if (commandType.equals(PIRES)) {
            new PiresAL(listener);
        }
    }

    @Override
    protected String getUnit() {
        return "%";
    }
}
