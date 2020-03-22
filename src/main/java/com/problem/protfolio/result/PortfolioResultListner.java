package com.problem.protfolio.result;

import com.google.common.eventbus.Subscribe;
import com.problem.protfolio.message.SecurityEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PortfolioResultListner {

    @Autowired
    Portfolio portfolio;

   @Subscribe
    private void getPortfolioData(String event) {
        if (event.equalsIgnoreCase("Publish"))
            System.out.println(portfolio.prettyPrint());
    }

}
