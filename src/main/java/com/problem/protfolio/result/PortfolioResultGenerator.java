package com.problem.protfolio.result;


import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.problem.protfolio.message.SecurityEvent;
import com.problem.protfolio.repo.SecurityRepository;
import com.problem.protfolio.schema.SecurityStaticData;
import com.problem.protfolio.utils.OptionCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLOutput;
import java.util.ArrayList;

@Component
public class PortfolioResultGenerator {

    @Autowired
    SecurityRepository securityRepository;

    @Autowired
    Portfolio portfolio;

    @Autowired
    EventBus eventBus;

    //update portfolio and send signal to portfolio result liner to publish
    @Subscribe
    private void getMarketData(SecurityEvent event) {
        portfolio.portfolioItemList.stream().filter(e -> e.getTicker().equalsIgnoreCase(event.getTicker()))
                                            .forEach(e -> e.setPrice(event.getPrice()));
        portfolio.portfolioItemList.stream().filter(e -> e.getUnderlying() != null)
                                            .filter(e -> e.getUnderlying().equalsIgnoreCase(event.getTicker()))
                                            .forEach(e -> e.setPrice(OptionCalculator.getOptionPrice(e.getTicker(), event.getPrice(),getSecurityData(e.getTicker()))));

        eventBus.post("Publish");
    }

    private SecurityStaticData getSecurityData(String ticker){
        return securityRepository.findById(ticker).get();
    }
}
