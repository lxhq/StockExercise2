package model.Repository;

import model.stock.Stock;

import java.util.List;

public interface Repository {

    /**
     * Create a stock(ticker) in this repository
     * @param ticker Stock symbol
     * @return True if successful
     */
    boolean createStock(String ticker);

    /**
     * Delete a stock(ticker) in this repository
     * @param ticker Stock symbol
     * @return True if successful
     */
    boolean deleteStock(String ticker);

    /**
     * Get all stocks from this repository
     * @return A list of stocks in this repository
     */
    List<Stock> getAllStocks();

    /**
     * Get a stock(ticker) from this repository
     * @param ticker Stock symbol
     * @return The Stock according to the ticker
     */
    Stock getStock(String ticker);
}
