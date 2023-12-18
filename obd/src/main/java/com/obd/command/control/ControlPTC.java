package com.obd.command.control;

import com.obd.command.Command;
import com.obd.command.CommandListener;
import com.obd.eltonvs.control.EltonvsPTCCommand;

public class ControlPTC extends Command {
    public ControlPTC(CommandListener listener) {
        super(listener);
    }

    @Override
    protected void run(CommandListener listener) {
        if (commandType.equals(ELTONVS)) {
            new EltonvsPTCCommand(listener);
        }
    }

    @Override
    protected String getUnit() {
        return "";
    }
}
