package model.stock;
import model.manager.StockManager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StockImpl implements Stock {

    private String stockSymbol;
    private List<Share> shares;
    private StockManager stockManager;

    /**
     * Constructs an object of Stock with its given parameter
     *
     * @param stockSymbol the given ticker symbol to create a stock object
     */
    public StockImpl(StockManager stockManager, String stockSymbol) {
        this.stockManager = stockManager;
        this.shares = new ArrayList<>();
        this.stockSymbol = stockSymbol;
        validTicker(stockSymbol);
    }

    @Override
    public String getStockSymbol() {
        return this.stockSymbol;
    }

    @Override
    public List<Share> getShares() {
        List<Share> res = new ArrayList<>();
        for (Share share : shares) {
            res.add(new Share(share.getShare(), share.getDate()));
        }
        return res;
    }

    @Override
    public void addShare(double share, LocalDate date) {
        try {
            stockValue(date);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
        this.shares.add(new Share(share, date.toString()));
    }

    @Override
    public void addAmount(double money, LocalDate date) {
        double share;
        try {
            share = money / stockValue(date);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
        this.shares.add(new Share(share, date.toString()));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Stock Ticker: " + this.stockSymbol + "\n");
        sb.append("Shares:\n");
        for (Share share : shares) {
            sb.append("Buy " + share.getShare() + " shares at " + share.getDate() + "\n");
        }
        return sb.toString();
    }

    private void validTicker(String stockSymbol) {
        if (!this.stockManager.isCached(stockSymbol)) {
            //this.stockManager.refresh(stockSymbol);
            if (!this.stockManager.isCached(stockSymbol)) {
                throw new IllegalArgumentException(
                        stockSymbol + ": " + "Sorry: there might be something wrong on your input. Try again please.");
            }
        }
    }

    /**
     * This method returns the stock prices at the given date.
     *
     * @param date the given parameter to check the stock value
     * @return the stock value
     */
    private double stockValue(LocalDate date) {
        String stockValue;
        stockValue = this.stockManager.searchStockValue(this.stockSymbol, date);
        if (!stockValue.isEmpty()) {
            return Double.valueOf(stockValue);
        }
        this.stockManager.refresh(this.stockSymbol);
        stockValue = this.stockManager.searchStockValue(this.stockSymbol, date);
        if (!stockValue.isEmpty()) {
            return Double.valueOf(stockValue);
        } else {
            throw new IllegalArgumentException(
                    "Sorry: we currently do not provide data on " + date.toString() +
                            " for " + this.stockSymbol + ".");
        }
    }
}