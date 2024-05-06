package com.techelevator.tenmo.model;

public class Transfer {
    private int fromUserId;
    private int toUserId;
    private double transferAmount;
    private int transferTypeId;
    private int transferStatusId;

    public Transfer(){

    }
    public Transfer(int fromUserId, int toUserId, double transferAmount, int transferTypeId, int transferStatusId) {
        this.fromUserId = fromUserId;
        this.toUserId = toUserId;
        this.transferAmount = transferAmount;
        this.transferTypeId = transferTypeId;
        this.transferStatusId = transferStatusId;
    }

    public int getTransferTypeId() {
        return transferTypeId;
    }

    public void setTransferTypeId(int transferTypeId) {
        this.transferTypeId = transferTypeId;
    }

    public int getTransferStatusId() {
        return transferStatusId;
    }

    public void setTransferStatusId(int transferStatusId) {
        this.transferStatusId = transferStatusId;
    }

    public int getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(int fromUserId) {
        this.fromUserId = fromUserId;
    }

    public int getToUserId() {
        return toUserId;
    }

    public void setToUserId(int toUserId) {
        this.toUserId = toUserId;
    }

    public double getTransferAmount() {
        return transferAmount;
    }

    public void setTransferAmount(double transferAmount) {
        this.transferAmount = transferAmount;
    }
// Transfer amount varies

}
