package com.obd.commands;

import android.bluetooth.BluetoothSocket;
import android.os.Handler;
import android.os.Looper;

import com.github.pires.obd.commands.ObdCommand;
import com.github.pires.obd.commands.engine.OilTempCommand;
import com.github.pires.obd.commands.temperature.AirIntakeTemperatureCommand;
import com.github.pires.obd.commands.temperature.AmbientAirTemperatureCommand;
import com.github.pires.obd.commands.temperature.EngineCoolantTemperatureCommand;
import com.obd.commands.control.ControlCommand;
import com.obd.commands.engine.EngineCommand;

public class Command {
    public static final String AIR_INTAKE_TEMPERATURE = "AIR_INTAKE_TEMPERATURE";
    public static final String AMBIENT_AIR_TEMPERATURE = "AMBIENT_AIR_TEMPERATURE";
    public static final String ENGINE_COOLANT_TEMPERATURE = "ENGINE_COOLANT_TEMPERATURE";
    public static final String ENGINE_OIL_TEMPERATURE = "ENGINE_OIL_TEMPERATURE";

    public static final int ECU_8_BIT = 8;
    public static final int ECU_16_BIT = 16;

    public static BluetoothSocket BLUETOOTH_SOCKET;

    public static int ECU_BIT = ECU_8_BIT;

    public static void run(String type, CommandListener listener) {
        new Thread(() -> {
            ObdCommand obdCommand = null;

            switch (type) {
                case ControlCommand.CONTROL_DTC:
                case ControlCommand.CONTROL_PENDING_TROUBLE_CODES:
                case ControlCommand.CONTROL_VIN:
                    obdCommand = new ControlCommand().getCommand(type);
                    break;
                case EngineCommand.ENGINE_ABSOLUTE_LOAD:
                case EngineCommand.ENGINE_MASS_AIR_FLOW:
                case EngineCommand.ENGINE_RPM:
                case EngineCommand.ENGINE_SPEED:
                case EngineCommand.ENGINE_THROTTLE_POSITION:
                    obdCommand = new EngineCommand().getCommand(type);
                    break;
                case AIR_INTAKE_TEMPERATURE:
                    obdCommand = new AirIntakeTemperatureCommand();
                    break;
                case AMBIENT_AIR_TEMPERATURE:
                    obdCommand = new AmbientAirTemperatureCommand();
                    break;
                case ENGINE_COOLANT_TEMPERATURE:
                    obdCommand = new EngineCoolantTemperatureCommand();
                    break;
                case ENGINE_OIL_TEMPERATURE:
                    obdCommand = new OilTempCommand();
                    break;
            }

            if (obdCommand != null) {
                ObdCommand command = obdCommand;

                try {
                    obdCommand.run(
                            BLUETOOTH_SOCKET.getInputStream(),
                            BLUETOOTH_SOCKET.getOutputStream());

                    new Handler(Looper.getMainLooper()).post(() ->
                            listener.onSuccess(
                                    command.getCalculatedResult(),
                                    command.getResultUnit())
                    );
                } catch (Exception e) {
                    e.printStackTrace();

                    new Handler(Looper.getMainLooper()).post(() ->
                            listener.onFailed(
                                    e.getMessage(),
                                    command.getResultUnit())
                    );
                }
            } else {
                new Handler(Looper.getMainLooper()).post(() ->
                        listener.onFailed(
                                "NULL",
                                "NULL")
                );
            }
        }).start();
    }

    public static boolean isEightBit() {
        return ECU_BIT == 8;
    }
}
