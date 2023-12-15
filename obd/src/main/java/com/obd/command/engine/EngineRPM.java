package com.obd.command.engine;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.annotation.NonNull;

import com.github.eltonvs.obd.command.ObdResponse;
import com.github.eltonvs.obd.command.engine.RPMCommand;
import com.github.eltonvs.obd.connection.ObdDeviceConnection;
import com.obd.command.Command;
import com.obd.command.CommandCache;
import com.obd.command.CommandListener;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;

public class EngineRPM extends Command<RPMCommand> {
    public EngineRPM(CommandListener listener) {
        super(listener);
    }

    @Override
    protected Runnable getRunnable(CommandListener listener) {
        return () -> {
            byte[] buffer = new byte[1024];

            int bytes;

            while (true) {
                try {
                    bytes = CommandCache.BLUETOOTH_SOCKET.getInputStream().read(buffer);

                    String data = new String(buffer, 0, bytes);

                    if (data.contains("RPM")) {
                        listener.onSuccess(
                                data.split(":")[1].trim(),
                                getUnit()
                        );
                    }
                } catch (Exception e) {
                    e.printStackTrace();

                    listener.onFailed("NULL", getUnit());
                    break;
                }
            }
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
    protected RPMCommand getCommand() {
        return new RPMCommand();
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
        return "RPM";
    }
}
