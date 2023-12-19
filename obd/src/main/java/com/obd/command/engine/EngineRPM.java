package com.obd.command.engine;

import com.obd.command.Command;
import com.obd.command.CommandListener;
import com.obd.eltonvs.engine.EltonvsRPM;
import com.obd.pires.engine.PiresRPM;

public class EngineRPM extends Command {
    public EngineRPM(CommandListener listener) {
        super(PIRES, listener);
    }

    @Override
    protected void run(CommandListener listener) {
        if (commandType.equals(ELTONVS)) {
            new EltonvsRPM(listener);
        } else if (commandType.equals(PIRES)) {
            new PiresRPM(listener);
        }
    }

    @Override
    protected String getUnit() {
        return "RPM";
    }
}
