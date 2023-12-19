package com.obd.pires.engine;

import android.os.Handler;
import android.os.Looper;

import com.github.pires.obd.commands.engine.MassAirFlowCommand;
import com.obd.command.CommandCache;
import com.obd.command.CommandListener;
import com.obd.pires.Command;

public class PiresMAF extends Command<MassAirFlowCommand> {
    public PiresMAF(CommandListener listener) {
        super(listener);
    }

    @Override
    protected Runnable getRunnable(CommandListener listener) {
        return () -> {
            obdCommand = new MassAirFlowCommand();

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
