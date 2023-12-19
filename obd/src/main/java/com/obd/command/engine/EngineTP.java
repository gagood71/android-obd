package com.obd.command.engine;

import com.obd.command.Command;
import com.obd.command.CommandListener;
import com.obd.eltonvs.engine.EltonvsTP;
import com.obd.pires.engine.PiresTP;

public class EngineTP extends Command {
    public EngineTP(CommandListener listener) {
        super(listener);
    }

    @Override
    protected void run(CommandListener listener) {
        if (commandType.equals(ELTONVS)) {
            new EltonvsTP(listener);
        } else if (commandType.equals(PIRES)) {
            new PiresTP(listener);
        }
    }

    @Override
    protected String getUnit() {
        return "%";
    }
}
