package com.obd.command.control;

import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;

import com.github.eltonvs.obd.command.ObdResponse;
import com.github.eltonvs.obd.command.control.DTCNumberCommand;
import com.github.eltonvs.obd.connection.ObdDeviceConnection;
import com.obd.command.Command;
import com.obd.command.CommandCache;
import com.obd.command.CommandListener;

import java.io.IOException;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;

public class ControlDTC extends Command<DTCNumberCommand> {
    public ControlDTC(CommandListener listener) {
        super(ELTONVS, listener);
    }

    @Override
    protected Runnable getRunnable(CommandListener listener) {
        return () -> {
            if (commandType.equals(ELTONVS)) {
                obdCommand = new DTCNumberCommand();

                try {
                    connection = new ObdDeviceConnection(
                            CommandCache.BLUETOOTH_SOCKET.getInputStream(),
                            CommandCache.BLUETOOTH_SOCKET.getOutputStream());

                    ObdResponse obdResponse = (ObdResponse) connection.run(
                            obdCommand,
                            USE_CACHE,
                            0,
                            MAX_RETRIES,
                            new Continuation<ObdResponse>() {
                                @NonNull
                                @Override
                                public CoroutineContext getContext() {
                                    return EmptyCoroutineContext.INSTANCE;
                                }

                                @Override
                                public void resumeWith(@NonNull Object o) {
                                }
                            }
                    );

                    if (obdResponse != null) {
                        new Handler(Looper.getMainLooper()).post(() ->
                                listener.onSuccess(
                                        obdResponse.getRawResponse().getValue(),
                                        obdResponse.getUnit()
                                )
                        );
                    }
                } catch (IOException e) {
                    new Handler(Looper.getMainLooper()).post(() ->
                            listener.onFailed(e.getMessage(), getUnit())
                    );
                }
            }
        };
    }

    @Override
    protected String getUnit() {
        return "codes";
    }
}
