package model.repository;

import model.stock.Stock;

import java.util.List;

public interface Repository {

    /**
     * Create a stock(ticker) in this repository
     * @param ticker Stock symbol
     */
    void createStock(String ticker);

    /**
     * Delete a stock(ticker) in this repository
     * @param ticker Stock symbol
     */
    void deleteStock(String ticker);

    /**
     * Get a stock(ticker) from this repository
     * @param ticker Stock symbol
     * @return The Stock according to the ticker
     */
    Stock getStock(String ticker);

    /**
     * Get all stocks from this repository
     * @return A list of stocks in this repository
     */
    List<Stock> getAllStocks();


}
