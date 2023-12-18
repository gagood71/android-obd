package com.obd.eltonvs.control;

import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;

import com.github.eltonvs.obd.command.ObdResponse;
import com.github.eltonvs.obd.command.control.PendingTroubleCodesCommand;
import com.github.eltonvs.obd.connection.ObdDeviceConnection;
import com.obd.command.CommandCache;
import com.obd.command.CommandListener;
import com.obd.eltonvs.Command;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;

public class EltonvsPTCCommand extends Command<PendingTroubleCodesCommand> {
    public EltonvsPTCCommand(CommandListener listener) {
        super(listener);
    }

    @Override
    protected Runnable getRunnable(CommandListener listener) {
        return () -> {
            obdCommand = new PendingTroubleCodesCommand();

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
                }
            } catch (Exception e) {
                new Handler(Looper.getMainLooper()).post(() ->
                        listener.onFailed(e.getMessage(), getUnit())
                );
            }
        };
    }
}
