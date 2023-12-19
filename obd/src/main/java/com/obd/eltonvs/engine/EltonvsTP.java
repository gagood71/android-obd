package com.obd.eltonvs.engine;

import android.os.Handler;
import android.os.Looper;

import com.github.eltonvs.obd.command.ObdResponse;
import com.github.eltonvs.obd.command.engine.ThrottlePositionCommand;
import com.github.eltonvs.obd.connection.ObdDeviceConnection;
import com.obd.command.CommandCache;
import com.obd.command.CommandListener;
import com.obd.eltonvs.Command;

public class EltonvsTP extends Command<ThrottlePositionCommand> {
    public EltonvsTP(CommandListener listener) {
        super(listener);
    }

    @Override
    protected Runnable getRunnable(CommandListener listener) {
        return () -> {
            obdCommand = new ThrottlePositionCommand();

            try {
                connection = new ObdDeviceConnection(
                        CommandCache.BLUETOOTH_SOCKET.getInputStream(),
                        CommandCache.BLUETOOTH_SOCKET.getOutputStream());

                ObdResponse obdResponse = (ObdResponse) connection.run(
                        obdCommand,
                        USE_CACHE,
                        0,
                        MAX_RETRIES,
                        continuation
                );

                if (obdResponse != null) {
                    new Handler(Looper.getMainLooper()).post(() ->
                            listener.onSuccess(
                                    obdResponse.getRawResponse().getValue(),
                                    getUnit()
                            )
                    );
                }
            } catch (Exception e) {
                new Handler(Looper.getMainLooper()).post(() ->
                        listener.onFailed(e.getMessage(), getUnit())
                );
            }
        };
    }
}
