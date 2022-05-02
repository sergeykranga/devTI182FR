package com.tmps.lab2.adapter.plugs;

import com.tmps.lab2.adapter.outlets.Volt;

public interface SocketPlug {
    boolean powerOn();
    void sendPower(Volt volts);
}
