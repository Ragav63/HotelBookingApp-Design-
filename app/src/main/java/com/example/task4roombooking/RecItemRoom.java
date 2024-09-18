package com.example.task4roombooking;

public class RecItemRoom {

    //Recycler items for Room items recycler in Room fragment
    private String roomTitleTv;
    private String roomDescriptionTv;
    private String roomPriceTv;

    private String roomTaxTv;

    public RecItemRoom(String roomTitleTv, String roomDescriptionTv, String roomPriceTv, String roomTaxTv) {
        this.roomTitleTv = roomTitleTv;
        this.roomDescriptionTv = roomDescriptionTv;
        this.roomPriceTv = roomPriceTv;
        this.roomTaxTv=roomTaxTv;
    }

    public String getRoomTitleTv() {
        return roomTitleTv;
    }

    public void setRoomTitleTv(String roomTitleTv) {
        this.roomTitleTv = roomTitleTv;
    }

    public String getRoomDescriptionTv() {
        return roomDescriptionTv;
    }

    public void setRoomDescriptionTv(String roomDescriptionTv) {
        this.roomDescriptionTv = roomDescriptionTv;
    }

    public String getRoomPriceTv() {
        return roomPriceTv;
    }

    public void setRoomPriceTv(String roomPriceTv) {
        this.roomPriceTv = roomPriceTv;
    }

    public String getRoomTaxTv() {
        return roomTaxTv;
    }

    public void setRoomTaxTv(String roomTaxTv) {
        this.roomTaxTv = roomTaxTv;
    }
}
