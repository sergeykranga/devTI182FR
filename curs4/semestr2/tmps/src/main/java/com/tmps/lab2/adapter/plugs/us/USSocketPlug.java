package com.tmps.lab2.adapter.plugs.us;

import com.tmps.lab2.adapter.outlets.Volt;
import com.tmps.lab2.adapter.plugs.SocketPlug;

public interface USSocketPlug extends SocketPlug {
    default void sendPower(Volt volts) {
        if (volts.getVolts() != 120) {
            throw new RuntimeException("Invalid voltage for US socket plug");
        } else {
            System.out.println("US socket plug received " + volts.getVolts() + " of power, powering on the device");
        }

        powerOn();
    }
}
