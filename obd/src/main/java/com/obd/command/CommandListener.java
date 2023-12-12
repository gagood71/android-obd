package com.obd.command;

public interface CommandListener {
    void onSuccess(String value, String unit);

    void onFailed(String value, String unit);
}