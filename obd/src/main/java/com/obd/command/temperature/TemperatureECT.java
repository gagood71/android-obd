package com.obd.command.temperature;

import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;

import com.core.configuration.Configuration;
import com.core.utils.LogUtil;
import com.github.eltonvs.obd.command.ObdResponse;
import com.github.eltonvs.obd.command.temperature.EngineCoolantTemperatureCommand;
import com.github.eltonvs.obd.connection.ObdDeviceConnection;
import com.obd.command.Command;
import com.obd.command.CommandListener;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;

public class TemperatureECT extends Command<EngineCoolantTemperatureCommand> {
    public TemperatureECT(CommandListener listener) {
        super(listener);
    }

    @Override
    protected Runnable getRunnable(CommandListener listener) {
        return () -> {};
    }

    @Override
    protected Runnable getRunnable(ObdDeviceConnection connection,
                                   CommandListener listener) {
        return () -> connection.run(
                obdCommand,
                USE_CACHE,
                Configuration.Time.TIME_0,
                MAX_RETRIES,
                getContinuation(listener)
        );
    }

    @Override
    protected EngineCoolantTemperatureCommand getCommand() {
        return new EngineCoolantTemperatureCommand();
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
                LogUtil.e(getClass().getName(), o.toString());

                if (o instanceof ObdResponse) {
                    ObdResponse obdResponse = (ObdResponse) o;

                    new Handler(Looper.getMainLooper()).post(() ->
                            listener.onSuccess(
                                    obdResponse.getRawResponse().getValue(),
                                    obdResponse.getUnit()
                            )
                    );
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
        return "°C";
    }
}
