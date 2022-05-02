package com.tmps.lab2.adapter.outlets.us;

import com.tmps.lab2.adapter.outlets.Volt;
import com.tmps.lab2.adapter.outlets.SocketOutlet;
import com.tmps.lab2.adapter.plugs.us.USSocketPlug;

public interface USSocketOutlet extends SocketOutlet<USSocketPlug> {
    default Volt getVolts() {
        return new Volt(120);
    }
}
