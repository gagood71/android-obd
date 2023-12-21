package com.obd.commands;

public interface CommandListener {
    void onSuccess(String value, String unit);

    void onFailed(String errorMessage, String unit);
}