package com.obd.pires.temperature;

import android.os.Handler;
import android.os.Looper;

import com.github.pires.obd.commands.temperature.EngineCoolantTemperatureCommand;
import com.obd.command.CommandCache;
import com.obd.command.CommandListener;
import com.obd.pires.Command;

public class PiresECT extends Command<EngineCoolantTemperatureCommand> {
    public PiresECT(CommandListener listener) {
        super(listener);
    }

    @Override
    protected Runnable getRunnable(CommandListener listener) {
        return () -> {
            obdCommand = new EngineCoolantTemperatureCommand();

            try {
                obdCommand.run(
                        CommandCache.BLUETOOTH_SOCKET.getInputStream(),
                        CommandCache.BLUETOOTH_SOCKET.getOutputStream());

                new Handler(Looper.getMainLooper()).post(() ->
                        listener.onSuccess(
                                obdCommand.getCalculatedResult(),
                                getUnit()
                        )
                );
            } catch (Exception e) {
                e.printStackTrace();

                new Handler(Looper.getMainLooper()).post(() ->
                        listener.onSuccess(
                                e.getMessage(),
                                getUnit()
                        )
                );
            }
        };
    }
}
