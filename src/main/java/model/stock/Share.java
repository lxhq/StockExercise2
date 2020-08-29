package model.stock;

public class Share {
    private String date;
    private double share;

    public void setDate(String date) {
        this.date = date;
    }

    public void setShare(double share) {
        this.share = share;
    }

    public Share(double share, String date) {
        this.share = share;
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public double getShare() {
        return share;
    }
}
