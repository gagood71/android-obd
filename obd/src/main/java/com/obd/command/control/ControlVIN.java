package com.obd.command.control;

import com.obd.command.Command;
import com.obd.command.CommandListener;
import com.obd.eltonvs.control.EltonvsVIN;

public class ControlVIN extends Command {
    public ControlVIN(CommandListener listener) {
        super(ELTONVS, listener);
    }

    @Override
    protected void run(CommandListener listener) {
        if (commandType.equals(ELTONVS)) {
            new EltonvsVIN(listener);
        }
    }

    @Override
    protected String getUnit() {
        return "";
    }
}
