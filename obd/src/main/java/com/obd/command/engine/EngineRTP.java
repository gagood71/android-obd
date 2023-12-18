package com.obd.command.engine;

import com.obd.command.Command;
import com.obd.command.CommandListener;
import com.obd.eltonvs.engine.EltonvsRTPCommand;
import com.obd.pires.engine.PiresTPCommand;

public class EngineRTP extends Command {
    public EngineRTP(CommandListener listener) {
        super(listener);
    }

    @Override
    protected void run(CommandListener listener) {
        if (commandType.equals(ELTONVS)) {
            new EltonvsRTPCommand(listener);
        } else if (commandType.equals(PIRES)) {
            new PiresTPCommand(listener);
        }
    }

    @Override
    protected String getUnit() {
        return "%";
    }
}
