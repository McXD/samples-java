package net.corda.samples.supplychain.clients.models;

public class Cargo {
    private String pickUpFrom;
    private String whereTo;
    private String Cargo;

    public Cargo() {}

    public Cargo(String pickUpFrom, String whereTo, String cargo) {
        this.pickUpFrom = pickUpFrom;
        this.whereTo = whereTo;
        Cargo = cargo;
    }

    public String getPickUpFrom() {
        return pickUpFrom;
    }

    public void setPickUpFrom(String pickUpFrom) {
        this.pickUpFrom = pickUpFrom;
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
