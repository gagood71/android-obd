package com.obd.command.control;

import com.obd.command.Command;
import com.obd.command.CommandListener;
import com.obd.eltonvs.control.EltonvsRTCCommand;

public class ControlRTC extends Command {
    public ControlRTC(CommandListener listener) {
        super(listener);
    }

    @Override
    protected void run(CommandListener listener) {
        if (commandType.equals(ELTONVS)) {
            new EltonvsRTCCommand(listener);
        }
    }

    @Override
    protected String getUnit() {
        return "";
    }
}
