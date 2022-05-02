package com.tmps.lab2.adapter.outlets;

import com.tmps.lab2.adapter.plugs.SocketPlug;

public interface SocketOutlet<T extends SocketPlug> {
    default void insertPlug(T socketPlug) {
        socketPlug.sendPower(getVolts());
    }
    Volt getVolts();
}
