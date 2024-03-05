package com.obd.commands.control;

import com.github.pires.obd.commands.ObdCommand;
import com.github.pires.obd.commands.control.DtcNumberCommand;
import com.github.pires.obd.commands.control.PendingTroubleCodesCommand;
import com.github.pires.obd.commands.control.VinCommand;

public class ControlCommand {
    public static final String CONTROL_DTC = "CONTROL_DTC";
    public static final String CONTROL_PENDING_TROUBLE_CODES = "CONTROL_PENDING_TROUBLE_CODES";
    public static final String CONTROL_VIN = "CONTROL_VIN";

    public ObdCommand getCommand(String type) {
        ObdCommand obdCommand = null;

        switch (type) {
            case CONTROL_DTC:
                obdCommand = new DtcNumberCommand();
                break;
            case CONTROL_PENDING_TROUBLE_CODES:
                obdCommand = new PendingTroubleCodesCommand();
                break;
            case CONTROL_VIN:
                obdCommand = new VinCommand();
                break;
        }

        return obdCommand;
    }
}
