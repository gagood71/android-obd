package com.obd.command;

public abstract class Command {
    public static final String DEFAULT = "DEFAULT";
    public static final String ELTONVS = "ELTONVS";
    public static final String PIRES = "PIRES";

    protected String commandType;

    public Command(CommandListener listener) {
        this(PIRES, listener);
    }

    protected Command(String type, CommandListener listener) {
        commandType = type;

        run(listener);
    }

    protected abstract void run(CommandListener listener);

    protected abstract String getUnit();
}
