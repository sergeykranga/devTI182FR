package com.tmps.lab2.adapter.outlets.eu;

import com.tmps.lab2.adapter.outlets.Volt;
import com.tmps.lab2.adapter.outlets.SocketOutlet;
import com.tmps.lab2.adapter.plugs.eu.EUSocketPlug;

public interface EUSocketOutlet extends SocketOutlet<EUSocketPlug> {
    default Volt getVolts() {
        return new Volt(240);
    }
}
