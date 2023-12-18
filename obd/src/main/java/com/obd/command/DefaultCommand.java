package com.obd.command;

public abstract class DefaultCommand<T> {
    protected T obdCommand;

    public DefaultCommand(CommandListener listener) {
        new Thread(getRunnable(listener)).start();
    }

    protected abstract Runnable getRunnable(CommandListener listener);

    public abstract String getUnit();
}
