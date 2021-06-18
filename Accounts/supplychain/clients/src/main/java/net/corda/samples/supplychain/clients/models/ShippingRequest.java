package net.corda.samples.supplychain.clients.models;

public class ShippingRequest {
    private String whoAmI;
    private String whereTo;
    private String Cargo;

    public ShippingRequest(){}

    public ShippingRequest(String whoAmI, String whereTo, String cargo) {
        this.whoAmI = whoAmI;
        this.whereTo = whereTo;
        Cargo = cargo;
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

    public String getCargo() {
        return Cargo;
    }

    public void setCargo(String cargo) {
        Cargo = cargo;
    }
}
