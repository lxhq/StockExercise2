package model.repository;

import json.BuyShare;
import model.stock.Stock;

import java.util.List;

public interface Repository {

    /**
     * Create a stock(ticker) in this repository
     * @param ticker Stock symbol
     */
    Stock createStock(String ticker);

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

    /**
     * Update a stock according information in busShare
     * @param buyShare A buyShare object which contains all information we need to update a Stock
     */
    Stock buyStock(BuyShare buyShare);

    /**
     * Delete a stock(ticker) in this repository
     * @param ticker Stock symbol
     */
    Stock deleteStock(String ticker);
}
