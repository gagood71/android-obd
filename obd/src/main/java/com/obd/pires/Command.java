package com.obd.pires;

import com.github.pires.obd.commands.ObdCommand;
import com.obd.command.CommandListener;
import com.obd.command.DefaultCommand;

public abstract class Command<T extends ObdCommand> extends DefaultCommand<T> {
    public Command(CommandListener listener) {
        super(listener);

        new Thread(getRunnable(listener)).start();
    }

    @Override
    public String getUnit() {
        return obdCommand.getResultUnit();
    }
}
