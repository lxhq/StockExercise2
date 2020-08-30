package model.repository;

import com.google.inject.Singleton;
import model.manager.StockManager;
import model.manager.StockManagerImpl;
import model.stock.Stock;
import model.stock.StockImpl;

import java.util.*;

@Singleton
public class RepositoryImpl implements Repository {

    private Map<String, Stock> stocks;
    private StockManager stockManager;

    public RepositoryImpl() {
        System.out.println("new Repository");
        stockManager = new StockManagerImpl();
        stocks = new HashMap<>();
    }

    @Override
    public void createStock(String ticker) {
        if (!stocks.containsKey(ticker)) {
            stocks.put(ticker, new StockImpl(stockManager, ticker));
            return;
        }
        throw new IllegalArgumentException();
    }

    @Override
    public void deleteStock(String ticker) {
        if (stocks.containsKey(ticker)) {
            stocks.remove(ticker);
            return;
        }
        throw new IllegalArgumentException();
    }

    @Override
    public List<Stock> getAllStocks() {
        return new ArrayList<>(stocks.values());
    }

    @Override
    public Stock getStock(String ticker) {
        if (stocks.containsKey(ticker)) {
            return stocks.get(ticker);
        }
        throw new IllegalArgumentException();
    }
}
