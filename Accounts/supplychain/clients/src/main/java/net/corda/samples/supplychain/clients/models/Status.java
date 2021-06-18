package net.corda.samples.supplychain.clients.models;

public class Status {
    private boolean success;

    public Status() {}

    public Status(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
