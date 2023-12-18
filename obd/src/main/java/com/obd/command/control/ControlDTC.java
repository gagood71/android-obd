package com.obd.command.control;

import com.obd.command.Command;
import com.obd.command.CommandListener;
import com.obd.eltonvs.control.EltonvsDTCCommand;

public class ControlDTC extends Command {
    public ControlDTC(CommandListener listener) {
        super(ELTONVS, listener);
    }

    @Override
    protected void run(CommandListener listener) {
        if (commandType.equals(ELTONVS)) {
            new EltonvsDTCCommand(listener);
        }
    }

    @Override
    protected String getUnit() {
        return "codes";
    }
}
