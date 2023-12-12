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
    protected Continuation<ObdResponse> continuation;

    public Command(CommandListener listener) {
        this(ELTONVS, listener);
    }

    protected Command(String type, CommandListener listener) {
        try {
            if (type.equals(DEFAULT)) {
                new Thread(getRunnable(listener)).start();
            } else if (type.equals(ELTONVS)) {
                obdCommand = getCommand();

                ObdDeviceConnection connection = new ObdDeviceConnection(
                        CommandCache.BLUETOOTH_SOCKET.getInputStream(),
                        CommandCache.BLUETOOTH_SOCKET.getOutputStream());

                new Thread(getRunnable(connection, listener)).start();
            }
        } catch (Exception e) {
            e.printStackTrace();

            listener.onFailed("", getUnit());
        }
    }

    protected abstract Runnable getRunnable(CommandListener listener);

    protected abstract Runnable getRunnable(ObdDeviceConnection connection,
                                            CommandListener listener);

    protected abstract T getCommand();

    protected abstract Continuation<ObdResponse> getContinuation(CommandListener listener);

    protected abstract String getUnit();
}
