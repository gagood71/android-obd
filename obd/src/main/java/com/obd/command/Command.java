package com.obd.command;

import com.github.eltonvs.obd.command.ObdCommand;
import com.github.eltonvs.obd.command.ObdResponse;
import com.github.eltonvs.obd.connection.ObdDeviceConnection;

import kotlin.coroutines.Continuation;

public abstract class Command<T extends ObdCommand> {
    public static final String DEFAULT = "DEFAULT";
    public static final String ELTONVS = "ELTONVS";

    protected static final int MAX_RETRIES = 5;

    protected static final boolean USE_CACHE = true;

    protected T obdCommand;
    protected ObdDeviceConnection connection;
    protected Continuation<ObdResponse> continuation;

    protected String commandType;

    public Command(CommandListener listener) {
        this(ELTONVS, listener);
    }

    protected Command(String type, CommandListener listener) {
        commandType = type;

        new Thread(getRunnable(listener)).start();
    }

    protected abstract Runnable getRunnable(CommandListener listener);

    protected abstract String getUnit();
}
