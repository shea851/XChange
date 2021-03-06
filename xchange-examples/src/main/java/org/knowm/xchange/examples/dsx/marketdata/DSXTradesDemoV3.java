package org.knowm.xchange.examples.dsx.marketdata;

import java.io.IOException;
import java.util.Map;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dsx.DSXExchangeV3;
import org.knowm.xchange.dsx.dto.marketdata.DSXTrade;
import org.knowm.xchange.dsx.service.DSXMarketDataServiceV3;
import org.knowm.xchange.dto.marketdata.Trades;
import org.knowm.xchange.service.marketdata.MarketDataService;

/** @author Mikhail Wall */
public class DSXTradesDemoV3 {

  public static void main(String[] args) throws IOException {

    Exchange dsx = ExchangeFactory.INSTANCE.createExchange(DSXExchangeV3.class);
    generic(dsx);
    raw(dsx);
  }

  private static void generic(Exchange exchange) throws IOException {

    MarketDataService marketDataService = exchange.getMarketDataService();

    Trades trades = marketDataService.getTrades(CurrencyPair.BTC_EUR);

    System.out.println(trades.toString());
  }

  private static void raw(Exchange exchange) throws IOException {

    DSXMarketDataServiceV3 marketDataService =
        (DSXMarketDataServiceV3) exchange.getMarketDataService();

    Map<String, DSXTrade[]> trades =
        marketDataService.getDSXTrades("btcusd", 7, "LIVE").getTradesMap();

    for (Map.Entry<String, DSXTrade[]> entry : trades.entrySet()) {
      System.out.println("Pair: " + entry.getKey() + ", Trades:");
      for (DSXTrade trade : entry.getValue()) {
        System.out.println(trade.toString());
      }
    }
  }
}
