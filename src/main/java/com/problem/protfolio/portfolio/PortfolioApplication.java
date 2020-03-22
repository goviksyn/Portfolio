package com.problem.protfolio.portfolio;

import com.google.common.eventbus.EventBus;
import com.problem.protfolio.marketdataprovider.DummyMarketDataProvider;
import com.problem.protfolio.result.PortfolioResultGenerator;
import com.problem.protfolio.repo.SecurityRepository;
import com.problem.protfolio.result.PortfolioResultListner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Scope;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;


@EnableAutoConfiguration
@ComponentScan(basePackages = "com.problem.protfolio")
@EnableJpaRepositories(basePackages = "com.problem.protfolio.repo")
@SpringBootApplication
@EntityScan(basePackages = {"com.problem.protfolio.schema","com.problem.protfolio.message"})
public class PortfolioApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(PortfolioApplication.class, args);
    }

    @Bean
    public TaskExecutor taskExecutor() {
        return new SimpleAsyncTaskExecutor();
    }

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public EventBus eventBus() {
        return new EventBus();
    }

    @Autowired
    SecurityRepository securityRepository;

    @Autowired
    PortfolioResultGenerator portfolioResultGenerator;

    @Autowired
    PortfolioResultListner portfolioResultListner;

    @Autowired
    DummyMarketDataProvider dummyMarketDataProvider;

    @Override
    public void run(String... args) throws Exception {
        eventBus().register(portfolioResultGenerator);
        eventBus().register(portfolioResultListner);
        taskExecutor().execute(dummyMarketDataProvider);
    }


}