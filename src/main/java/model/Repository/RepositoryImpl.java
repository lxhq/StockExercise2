package model.Repository;

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
    public boolean createStock(String ticker) {
        if (stocks.containsKey(ticker)) {
            return false;
        } else {
            stocks.put(ticker, new StockImpl(stockManager, ticker));
            return true;
        }
    }

    @Override
    public boolean deleteStock(String ticker) {
        if (stocks.containsKey(ticker)) {
            stocks.remove(ticker);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Stock> getAllStocks() {
        return new ArrayList<>(stocks.values());
    }

    @Override
    public Stock getStock(String ticker) {
        return stocks.get(ticker);
    }


}
