package com.example.task4roombooking;

public class RecItOccRoom {

    //Recycler items for Room items recycler in Room fragment
    private String roomOccType;
    private String roomOccPrice;
    private String roomOccTax;

    public RecItOccRoom(String roomOccType, String roomOccPrice, String roomOccTax) {
        this.roomOccType = roomOccType;
        this.roomOccPrice = roomOccPrice;
        this.roomOccTax = roomOccTax;
    }

    public String getRoomOccType() {
        return roomOccType;
    }

    public void setRoomOccType(String roomOccType) {
        this.roomOccType = roomOccType;
    }

    public String getRoomOccPrice() {
        return roomOccPrice;
    }

    public void setRoomOccPrice(String roomOccPrice) {
        this.roomOccPrice = roomOccPrice;
    }

    public String getRoomOccTax() {
        return roomOccTax;
    }

    public void setRoomOccTax(String roomOccTax) {
        this.roomOccTax = roomOccTax;
    }
}
