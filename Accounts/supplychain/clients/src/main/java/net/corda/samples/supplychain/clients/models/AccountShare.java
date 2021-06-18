package net.corda.samples.supplychain.clients.models;

public class AccountShare {
    private String senderNode;
    private String receiverNode;
    private String acctName;

    public AccountShare(){}

    public AccountShare(String senderNode, String receiverNode, String acctName) {
        this.senderNode = senderNode;
        this.receiverNode = receiverNode;
        this.acctName = acctName;
    }

    public String getSenderNode() {
        return senderNode;
    }

    public void setSenderNode(String senderNode) {
        this.senderNode = senderNode;
    }

    public String getReceiverNode() {
        return receiverNode;
    }

    public void setReceiverNode(String receiverNode) {
        this.receiverNode = receiverNode;
    }

    public String getAcctName() {
        return acctName;
    }

    public void setAcctName(String acctName) {
        this.acctName = acctName;
    }

    @Override
    public String toString() {
        return "AccountShare{" +
                "senderNode='" + senderNode + '\'' +
                ", receiverNode='" + receiverNode + '\'' +
                ", acctName='" + acctName + '\'' +
                '}';
    }
}
