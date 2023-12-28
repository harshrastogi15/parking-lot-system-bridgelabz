package com.bridgelabz.entity;

public class SecurityPersonal {

    public String message;

    public SecurityPersonal(){
        this.message = "Parking Lot is not full";
    }

    public void updateMessage(Boolean flag){
        if(flag){
            this.message = "Parking Lot is full";
        }
        else{
            this.message = "Parking Lot is not full";
        }
    }

}
