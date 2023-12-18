package com.obd.command.engine;

import com.obd.command.Command;
import com.obd.command.CommandListener;
import com.obd.eltonvs.engine.EltonvsTPCommand;
import com.obd.pires.engine.PiresTPCommand;

public class EngineTP extends Command {
    public EngineTP(CommandListener listener) {
        super(listener);
    }

    @Override
    protected void run(CommandListener listener) {
        if (commandType.equals(ELTONVS)) {
            new EltonvsTPCommand(listener);
        } else if (commandType.equals(PIRES)) {
            new PiresTPCommand(listener);
        }
    }

    @Override
    protected String getUnit() {
        return "%";
    }
}
