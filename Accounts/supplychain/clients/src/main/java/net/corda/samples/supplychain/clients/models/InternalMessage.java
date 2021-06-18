package net.corda.samples.supplychain.clients.models;

public class InternalMessage {
    private String onNode;
    private String whoAmI;
    private String whereTo;
    private String message;

    public InternalMessage(){}

    public InternalMessage(String onNode, String whoAmI, String whereTo, String message) {
        this.onNode = onNode;
        this.whoAmI = whoAmI;
        this.whereTo = whereTo;
        this.message = message;
    }

    public String getOnNode() {
        return onNode;
    }

    public void setOnNode(String onNode) {
        this.onNode = onNode;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
