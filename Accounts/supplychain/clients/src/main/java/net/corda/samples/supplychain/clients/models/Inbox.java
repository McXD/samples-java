package net.corda.samples.supplychain.clients.models;

import java.util.List;

public class Inbox {
    private String fromNode;
    private String ofAcct;
    private List<String> messages;

    public Inbox() {}

    public Inbox(String fromNode, String ofAcct, List<String> messages) {
        this.fromNode = fromNode;
        this.ofAcct = ofAcct;
        this.messages = messages;
    }

    public String getFromNode() {
        return fromNode;
    }

    public void setFromNode(String fromNode) {
        this.fromNode = fromNode;
    }

    public String getOfAcct() {
        return ofAcct;
    }

    public void setOfAcct(String ofAcct) {
        this.ofAcct = ofAcct;
    }

    public List<String> getMessage() {
        return messages;
    }

    public void setMessage(List<String> messages) {
        this.messages = messages;
    }
}
