package com.obd.beta;

import com.obd.commands.CommandListener;

import java.io.InputStream;
import java.io.OutputStream;

public abstract class OBDCommand<T> {
    protected InputStream inputStream;

    protected OutputStream outputStream;

    protected CommandListener listener;

    public OBDCommand() {
    }

    protected abstract T getValue();

    protected abstract String getUnit();

    protected abstract byte[] getCommand();

    public void run(InputStream input,
                    OutputStream output,
                    CommandListener commandListener) {
        inputStream = input;
        outputStream = output;
        listener = commandListener;
    }
}
