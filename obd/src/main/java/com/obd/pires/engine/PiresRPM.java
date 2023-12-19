package com.obd.pires.engine;

import android.os.Handler;
import android.os.Looper;

import com.github.pires.obd.commands.engine.RPMCommand;
import com.obd.command.CommandCache;
import com.obd.command.CommandListener;
import com.obd.pires.Command;
import com.obd.pires.HexadecimalRPM;
import com.obd.pires.OctalRPM;

public class PiresRPM extends Command<RPMCommand> {
    public PiresRPM(CommandListener listener) {
        super(listener);
    }

    @Override
    protected Runnable getRunnable(CommandListener listener) {
        return () -> {
            if (CommandCache.ECU_BIT == CommandCache.ECU_16_BIT) {
                obdCommand = new HexadecimalRPM();
            } else {
                obdCommand = new OctalRPM();
            }

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
