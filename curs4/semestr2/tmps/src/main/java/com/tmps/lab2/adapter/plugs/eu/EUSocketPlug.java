package com.tmps.lab2.adapter.plugs.eu;

import com.tmps.lab2.adapter.outlets.Volt;
import com.tmps.lab2.adapter.plugs.SocketPlug;

public interface EUSocketPlug extends SocketPlug {
    default void sendPower(Volt volts) {
        if (volts.getVolts() != 240) {
            throw new RuntimeException("Invalid voltage for EU socket plug");
        } else {
            System.out.println("EU socket plug received " + volts.getVolts() + " of power, powering on the device");
        }
    }
}
