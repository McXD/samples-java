package net.corda.samples.supplychain.clients.models;

public class Payment {
    private String whoAmI;
    private String whereTo;
    private int amount;

    public Payment(){}

    public Payment(String whoAmI, String whereTo, int amount) {
        this.whoAmI = whoAmI;
        this.whereTo = whereTo;
        this.amount = amount;
    }

    public String getWhoAmI() {
        return whoAmI;
    }

    public void setWhoAmI(String whoAmI) {
        this.whoAmI = whoAmI;
    }

    public String getWhereTo() {
        return whereTo;
    }

    public void setWhereTo(String whereTo) {
        this.whereTo = whereTo;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
