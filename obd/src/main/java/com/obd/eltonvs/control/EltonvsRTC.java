package com.obd.eltonvs.control;

import android.os.Handler;
import android.os.Looper;

import com.github.eltonvs.obd.command.ObdResponse;
import com.github.eltonvs.obd.command.control.ResetTroubleCodesCommand;
import com.github.eltonvs.obd.connection.ObdDeviceConnection;
import com.obd.command.CommandCache;
import com.obd.command.CommandListener;
import com.obd.eltonvs.Command;

public class EltonvsRTC extends Command<ResetTroubleCodesCommand> {
    public EltonvsRTC(CommandListener listener) {
        super(listener);
    }

    @Override
    protected Runnable getRunnable(CommandListener listener) {
        return () -> {
            obdCommand = new ResetTroubleCodesCommand();

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
                                    obdResponse.getUnit()
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
