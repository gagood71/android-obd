package com.obd.beta;

import android.os.Handler;
import android.os.Looper;

import com.obd.commands.CommandListener;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class RPMCommand extends OBDCommand<Integer> {
    public RPMCommand() {
        super();
    }

    @Override
    protected Integer getValue() {
        try {
            outputStream.write(getCommand());
        } catch (IOException e) {
            e.printStackTrace();

            new Handler(Looper.getMainLooper()).post(() ->
                    listener.onFailed(e.getMessage(), getUnit())
            );
        }

        byte[] response = new byte[4];

        try {
            if (inputStream.read(response) != response.length) {
                return -1;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (response[0] != 0x41) {
            return -1;
        }

        return (response[2] + response[3]);
    }

    @Override
    protected String getUnit() {
        return "RPM";
    }

    @Override
    protected byte[] getCommand() {
        return new byte[]{0x01, 0x0D};
    }

    @Override
    public void run(InputStream input,
                    OutputStream output,
                    CommandListener commandListener) {
        super.run(input, output, commandListener);

        new Handler(Looper.getMainLooper()).post(() ->
                listener.onSuccess(String.valueOf(getValue()), getUnit())
        );
    }
}
