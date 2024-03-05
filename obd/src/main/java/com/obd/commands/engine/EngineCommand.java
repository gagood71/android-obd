package com.obd.commands.engine;

import com.github.pires.obd.commands.ObdCommand;

public class EngineCommand {
    public static final String ENGINE_ABSOLUTE_LOAD = "ENGINE_ABSOLUTE_LOAD";
    public static final String ENGINE_MASS_AIR_FLOW = "ENGINE_MASS_AIR_FLOW";
    public static final String ENGINE_RPM = "ENGINE_RPM";
    public static final String ENGINE_SPEED = "ENGINE_SPEED";
    public static final String ENGINE_THROTTLE_POSITION = "ENGINE_THROTTLE_POSITION";

    public ObdCommand getCommand(String type) {
        ObdCommand obdCommand = null;

        switch (type) {
            case ENGINE_ABSOLUTE_LOAD:
                obdCommand = new AbsoluteLoad();
                break;
            case ENGINE_MASS_AIR_FLOW:
                obdCommand = new MassAirFlow();
                break;
            case ENGINE_RPM:
                obdCommand = new RPM();
                break;
            case ENGINE_SPEED:
                obdCommand = new Speed();
                break;
            case ENGINE_THROTTLE_POSITION:
                obdCommand = new ThrottlePosition();
                break;
        }

        return obdCommand;
    }
}
