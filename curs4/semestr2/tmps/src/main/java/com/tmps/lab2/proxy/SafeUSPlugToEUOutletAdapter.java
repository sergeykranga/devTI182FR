package com.tmps.lab2.proxy;

import java.util.Random;

import com.tmps.lab2.adapter.USPlugToEUOutletAdapter;
import com.tmps.lab2.adapter.outlets.us.USSocketOutlet;
import com.tmps.lab2.adapter.plugs.eu.EUSocketPlug;
import com.tmps.lab2.adapter.plugs.us.USSocketPlug;

public class SafeUSPlugToEUOutletAdapter implements USSocketOutlet, EUSocketPlug {
    private final USPlugToEUOutletAdapter usPlugToEUOutletAdapter;

    public SafeUSPlugToEUOutletAdapter() {
        this.usPlugToEUOutletAdapter = new USPlugToEUOutletAdapter();
    }

    @Override
    public void insertPlug(USSocketPlug socketPlug) {
        while (true) {
            boolean isElectricalSurgePresent = new Random().nextInt() % 2 == 0;

            if (isElectricalSurgePresent) {
                System.out.println("Safe adapter detected an electrical surge, waiting for electricity to stabilize...");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                continue;
            }

            System.out.println("Safe adapter did not detect electrical surges, allowing electricity to flow...");
            break;
        }
        usPlugToEUOutletAdapter.insertPlug(socketPlug);
    }

    @Override
    public boolean powerOn() {
        return usPlugToEUOutletAdapter.powerOn();
    }
}
