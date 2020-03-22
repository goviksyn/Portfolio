package com.problem.protfolio.marketdataprovider;

import com.google.common.eventbus.EventBus;
import com.problem.protfolio.message.SecurityEvent;
import com.problem.protfolio.repo.SecurityEventRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class DummyMarketDataProvider implements Runnable {

    @Autowired
    EventBus eventBus;

    @Autowired
    SecurityEventRepo  securityEventRepo;

    private ArrayList<SecurityEvent> supportedScripts = new ArrayList();

    //Supported script
    public void init() {
        securityEventRepo.findAll().forEach(e -> supportedScripts.add(e));
    }


    private void randomPrice(SecurityEvent securityEvent) {
        double sp = securityEvent.getPrice();
        sp += Math.random() >= 0.5 ? +0.1 : -0.1;
        securityEvent.setPrice(sp);

    }

    private SecurityEvent updatePrice(SecurityEvent se) {
        randomPrice(se);
        return se;
    }

    //Post event every 2 second
    @Override
    public void run() {
        init();
        while (true) {
            supportedScripts.forEach(e -> eventBus.post(updatePrice(e)));
            //Sleeping for 2 second to publish next market data
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
