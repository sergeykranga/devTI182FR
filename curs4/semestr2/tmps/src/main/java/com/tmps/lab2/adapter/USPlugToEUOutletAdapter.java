package com.tmps.lab2.adapter;

import com.tmps.lab2.adapter.outlets.us.USSocketOutlet;
import com.tmps.lab2.adapter.plugs.eu.EUSocketPlug;
import com.tmps.lab2.adapter.plugs.us.USSocketPlug;

public class USPlugToEUOutletAdapter implements USSocketOutlet, EUSocketPlug  {

    @Override
    public void insertPlug(USSocketPlug socketPlug) {
        powerOn();
        socketPlug.sendPower(getVolts());
    }

    @Override
    public boolean powerOn() {
        System.out.println("Powering on the adapter using " + getVolts().getVolts() + " volts...");
        return true;
    }
}
