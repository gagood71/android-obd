package com.obd.eltonvs;

import com.github.eltonvs.obd.command.ObdCommand;
import com.github.eltonvs.obd.command.ObdResponse;
import com.github.eltonvs.obd.connection.ObdDeviceConnection;
import com.obd.command.CommandListener;
import com.obd.command.DefaultCommand;

import kotlin.coroutines.Continuation;

public abstract class Command<T extends ObdCommand> extends DefaultCommand<T> {
    protected static final int MAX_RETRIES = 5;

    protected static final boolean USE_CACHE = true;

    protected ObdDeviceConnection connection;
    protected Continuation<ObdResponse> continuation;

    protected String commandType;

    public Command(CommandListener listener) {
        super(listener);

        new Thread(getRunnable(listener)).start();
    }

    @Override
    public String getUnit() {
        return obdCommand.getDefaultUnit();
    }
}
