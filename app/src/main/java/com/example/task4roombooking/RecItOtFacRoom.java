package com.example.task4roombooking;

public class RecItOtFacRoom {

    //Recycler items for Room items recycler in Room fragment
    private String roomOFCBox;
    private String roomOFPrice;
    private boolean isChecked; // Add a field to track checkbox state
    public RecItOtFacRoom(String roomOFCBox, String roomOFPrice) {
        this.roomOFCBox = roomOFCBox;
        this.roomOFPrice = roomOFPrice;
        this.isChecked=false;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public String getRoomOFCBox() {
        return roomOFCBox;
    }

    public void setRoomOFCBox(String roomOFCBox) {
        this.roomOFCBox = roomOFCBox;
    }

    public String getRoomOFPrice() {
        return roomOFPrice;
    }

    public void setRoomOFPrice(String roomOFPrice) {
        this.roomOFPrice = roomOFPrice;
    }
}
