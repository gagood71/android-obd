package com.obd.command.temperature;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.annotation.NonNull;

import com.github.eltonvs.obd.command.ObdResponse;
import com.github.eltonvs.obd.command.temperature.OilTemperatureCommand;
import com.github.eltonvs.obd.connection.ObdDeviceConnection;
import com.obd.command.Command;
import com.obd.command.CommandListener;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;

public class TemperatureOT extends Command<OilTemperatureCommand> {
    public TemperatureOT(CommandListener listener) {
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
    protected OilTemperatureCommand getCommand() {
        return new OilTemperatureCommand();
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

                    new Handler(Looper.getMainLooper()).post(() ->
                            listener.onSuccess(
                                    obdResponse.getRawResponse().getValue(),
                                    obdResponse.getUnit()
                            )
                    );
                }
            }
        };
    }

    @Override
    protected String getUnit() {
        return "°C";
    }
}
