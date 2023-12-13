package com.obd.command.control;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.annotation.NonNull;

import com.github.eltonvs.obd.command.ObdResponse;
import com.github.eltonvs.obd.command.control.PendingTroubleCodesCommand;
import com.github.eltonvs.obd.connection.ObdDeviceConnection;
import com.obd.command.Command;
import com.obd.command.CommandListener;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;

public class ControlPTC extends Command<PendingTroubleCodesCommand> {
    public ControlPTC(CommandListener listener) {
        super(listener);
    }

    @Override
    protected Runnable getRunnable(CommandListener listener) {
        return () -> {
        };
    }

    @Override
    protected Runnable getRunnable(ObdDeviceConnection connection,
                                   CommandListener listener) {
        return () -> connection.run(
                obdCommand,
                USE_CACHE,
                0,
                MAX_RETRIES,
                getContinuation(listener)
        );
    }

    @Override
    protected PendingTroubleCodesCommand getCommand() {
        return new PendingTroubleCodesCommand();
    }

    @Override
    protected Continuation<ObdResponse> getContinuation(CommandListener listener) {
        return new Continuation<ObdResponse>() {
            @NonNull
            @Override
            public CoroutineContext getContext() {
                return EmptyCoroutineContext.INSTANCE;
            }

            @Override
            public void resumeWith(@NonNull Object o) {
                Log.e(getClass().getName(), o.toString());

                if (o instanceof ObdResponse) {
                    ObdResponse obdResponse = (ObdResponse) o;

                    new Handler(Looper.getMainLooper()).post(() -> {
                        String value = obdCommand.getTroubleCodesList().toString();

                        if (value.equals("") || value.equals("[]")) {
                            return;
                        }

                        value = value.replace("[", "");
                        value = value.replace("]", "");

                        listener.onSuccess(
                                value,
                                obdResponse.getUnit()
                        );
                    });
                } else {
                    new Handler(Looper.getMainLooper()).post(() ->
                            listener.onFailed(
                                    o.toString(),
                                    obdCommand.getDefaultUnit()
                            )
                    );
                }
            }
        };
    }

    @Override
    protected String getUnit() {
        return "";
    }
}
