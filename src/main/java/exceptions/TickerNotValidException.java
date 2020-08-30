package exceptions;

public class TickerNotValidException extends Exception {
    private String message;

    public TickerNotValidException() {
        this.message = "This ticker is not valid";
    }

    public TickerNotValidException(String message) {
        this.message = message;
    }
}
