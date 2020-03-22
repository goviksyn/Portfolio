1] Code read portfolio information from portfoliodata.csv present in resources folder.
2] Supported scripts [stocks/contracts] can be viewed in table SECURITY_EVENT.
3] H2 console can be viewed at http://localhost:8080/h2-console
4] Current project only support random stock pricing.
5] pub-sub is implemented using Google Gauva EventBus. 
6] OptionCalculator does option pricing.


RUN
1] PortfolioApplication.java is entry point.
2] If all dependencies are in place every 2 seconds entire portfolio will be printed on the 
console.

Flow
1] DummyMarketDataProvider keep pushing market data every 2 sec to the event bus.
2] PortfolioResultGenerator does all prising and signal PortfolioResultListner to render portfolio.

