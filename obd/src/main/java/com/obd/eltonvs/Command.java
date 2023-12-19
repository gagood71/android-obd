package com.obd.eltonvs;

import androidx.annotation.NonNull;

import com.github.eltonvs.obd.command.ObdCommand;
import com.github.eltonvs.obd.command.ObdResponse;
import com.github.eltonvs.obd.connection.ObdDeviceConnection;
import com.obd.command.CommandListener;
import com.obd.command.DefaultCommand;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;

public abstract class Command<T extends ObdCommand> extends DefaultCommand<T> {
    protected static final int MAX_RETRIES = 5;

    protected static final boolean USE_CACHE = true;

    protected ObdDeviceConnection connection;
    protected Continuation<ObdResponse> continuation;

    protected String commandType;

    public Command(CommandListener listener) {
        super(listener);

        continuation = getContinuation();

        new Thread(getRunnable(listener)).start();
    }

    protected Continuation<ObdResponse> getContinuation() {
        return new Continuation<ObdResponse>() {
            @NonNull
            @Override
            public CoroutineContext getContext() {
                return EmptyCoroutineContext.INSTANCE;
            }

            @Override
            public void resumeWith(@NonNull Object o) {
            }
        };
    }

    @Override
    public String getUnit() {
        return obdCommand.getDefaultUnit();
    }
}
