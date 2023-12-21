package com.obd.command;

import android.bluetooth.BluetoothSocket;
import android.os.Handler;
import android.os.Looper;

import com.github.pires.obd.commands.ObdCommand;
import com.github.pires.obd.commands.control.DtcNumberCommand;
import com.github.pires.obd.commands.control.PendingTroubleCodesCommand;
import com.github.pires.obd.commands.control.VinCommand;
import com.github.pires.obd.commands.engine.OilTempCommand;
import com.github.pires.obd.commands.temperature.AirIntakeTemperatureCommand;
import com.github.pires.obd.commands.temperature.AmbientAirTemperatureCommand;
import com.github.pires.obd.commands.temperature.EngineCoolantTemperatureCommand;
import com.obd.command.engine.AbsoluteLoad;
import com.obd.command.engine.MassAirFlow;
import com.obd.command.engine.RPM;
import com.obd.command.engine.Speed;
import com.obd.command.engine.ThrottlePosition;

public class Command {
    public static final String ABSOLUTE_LOAD = "ABSOLUTE_LOAD";
    public static final String AIR_INTAKE_TEMPERATURE = "AIR_INTAKE_TEMPERATURE";
    public static final String AMBIENT_AIR_TEMPERATURE = "AMBIENT_AIR_TEMPERATURE";
    public static final String DTC = "DTC";
    public static final String ENGINE_COOLANT_TEMPERATURE = "ENGINE_COOLANT_TEMPERATURE";
    public static final String ENGINE_OIL_TEMPERATURE = "ENGINE_OIL_TEMPERATURE";
    public static final String ENGINE_RPM = "ENGINE_RPM";
    public static final String MASS_AIR_FLOW = "MASS_AIR_FLOW";
    public static final String PENDING_TROUBLE_CODES = "PENDING_TROUBLE_CODES";
    public static final String SPEED = "SPEED";
    public static final String THROTTLE_POSITION = "THROTTLE_POSITION";
    public static final String VIN = "VIN";

    private static BluetoothSocket BLUETOOTH_SOCKET;

    private static int ECU_BIT = 8;

    public static void run(String type, CommandListener listener) {
        new Thread(() -> {
            ObdCommand obdCommand = null;

            switch (type) {
                case ABSOLUTE_LOAD:
                    obdCommand = new AbsoluteLoad();
                    break;
                case AIR_INTAKE_TEMPERATURE:
                    obdCommand = new AirIntakeTemperatureCommand();
                    break;
                case AMBIENT_AIR_TEMPERATURE:
                    obdCommand = new AmbientAirTemperatureCommand();
                    break;
                case DTC:
                    obdCommand = new DtcNumberCommand();
                    break;
                case ENGINE_COOLANT_TEMPERATURE:
                    obdCommand = new EngineCoolantTemperatureCommand();
                    break;
                case ENGINE_OIL_TEMPERATURE:
                    obdCommand = new OilTempCommand();
                    break;
                case ENGINE_RPM:
                    obdCommand = new RPM();
                    break;
                case MASS_AIR_FLOW:
                    obdCommand = new MassAirFlow();
                    break;
                case PENDING_TROUBLE_CODES:
                    obdCommand = new PendingTroubleCodesCommand();
                    break;
                case SPEED:
                    obdCommand = new Speed();
                    break;
                case THROTTLE_POSITION:
                    obdCommand = new ThrottlePosition();
                    break;
                case VIN:
                    obdCommand = new VinCommand();
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

    public static void setBluetoothSocket(BluetoothSocket bluetoothSocket) {
        BLUETOOTH_SOCKET = bluetoothSocket;
    }

    public static void setEcuBit(int ecuBit) {
        ECU_BIT = ecuBit;
    }

    public static boolean is8Bit() {
        return ECU_BIT == 8;
    }
}
