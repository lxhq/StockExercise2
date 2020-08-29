package model.stock;

import java.time.LocalDate;
import java.util.List;

/**
 * This is the Stock interface which requires some methods that its implementation should
 * override.
 */
public interface Stock {

    /**
     * This method returns the stock ticker
     *
     * @return the symbol of the stock
     */
    String getStockSymbol();

    /**
     * Get all shares for this stock
     * @return a list of shares
     */
    List<Share> getShares();

    /**
     * Buy certain amount of shares{share} in a given date{date}
     *
     * @param share the given number to buy in
     * @param date  the given date to buy in
     */
    void addShare(double share, LocalDate date);

    /**
     * Buy this stock by using certain amount of money{money} at a given date{date}
     *
     * @param money         the given amount to invest
     * @param date          the given date
     */
    void addAmount(double money, LocalDate date);
}