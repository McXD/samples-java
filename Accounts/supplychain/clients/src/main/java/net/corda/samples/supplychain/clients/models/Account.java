package net.corda.samples.supplychain.clients.models;

public class Account {
    private String onNode;
    private String acctName;

    public Account(){}

    public Account(String onNode, String acctName) {
        this.onNode = onNode;
        this.acctName = acctName;
    }

    public String getOnNode() {
        return onNode;
    }

    public void setOnNode(String onNode) {
        this.onNode = onNode;
    }

    public String getAcctName() {
        return acctName;
    }

    public void setAcctName(String acctName) {
        this.acctName = acctName;
    }

    @Override
    public String toString(){
        return String.format("{onNode: %s, acctName: %s}", onNode, acctName);
    }
}
