package com.problem.protfolio.result;

import com.problem.protfolio.repo.SecurityEventRepo;
import com.problem.protfolio.repo.SecurityRepository;
import com.problem.protfolio.schema.SecurityStaticData;
import com.problem.protfolio.utils.OptionCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class Portfolio {
    List<PortfolioItem> portfolioItemList = new ArrayList<>();
    Double NAV = 0.0;

    @Autowired
    ResourceLoader resourceLoader;

    @Autowired
    SecurityRepository securityRepository;

    @Autowired
    SecurityEventRepo securityEventRepo;

    private void update() {
        this.NAV = portfolioItemList.stream().map(e -> e.getPrice() * e.getQuantity()).reduce((a, b) -> a + b).get();
    }

    @PostConstruct
    private void loadPortfolioFromCSV() {
        try {
            Scanner scanner = new Scanner(resourceLoader.getResource("classpath:portfoliodata.csv").getFile());
            while (scanner.hasNext()) {
                String input = scanner.next();
                String[] split = input.split(",");
                PortfolioItem portfolioItem = new PortfolioItem();
                portfolioItem.setTicker(split[0]);
                portfolioItem.setQuantity(Integer.valueOf(split[1]));
                portfolioItemList.add(portfolioItem);
                securityRepository.findById(split[0]).ifPresent(e -> portfolioItem.setUnderlying(e.getUnderlying()));
            }
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        portfolioItemList.forEach(e -> securityEventRepo.findById(e.getTicker()).ifPresent(k -> e.setPrice(k.getPrice())));

        portfolioItemList.stream().filter(e -> e.getUnderlying() != null)
                .forEach(e -> e.setPrice(OptionCalculator.getOptionPrice(e.getTicker(), getUndPrice(e.getUnderlying()),getSecurityData(e.getTicker()))));

    }

    private SecurityStaticData getSecurityData(String ticker){
        return securityRepository.findById(ticker).get();
    }
    private double getUndPrice(String ticker){
        return securityEventRepo.findById(ticker).get().getPrice();
    }

    @Override
    public String toString() {
        update();
        String separator = "\n ------------------------------------------------------------------------------------------------------ \n";
        String portfolio = portfolioItemList.stream().map(e -> "     Ticker " + e.getTicker() + " CurrentMarket price " + e.getPrice() + "\n").collect(Collectors.joining());
        String currentNAV = "     Total portfolio NAV " + NAV;
        return separator + portfolio + currentNAV + separator;
    }

    public String prettyPrint(){
        return toString();
    }
}
