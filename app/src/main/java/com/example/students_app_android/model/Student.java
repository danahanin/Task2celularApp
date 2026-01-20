package com.example.students_app_android.model;

public class Student {
    String id;
    String name;
    int imageId;
    String phoneNumber;
    String address;
    boolean isChecked;

    public Student(String id,
                   String name,
                   int imageId,
                   String phoneNumber,
                   String address,
                   boolean isChecked) {
        this.id = id;
        this.name = name;
        this.imageId = imageId;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.isChecked = isChecked;
    }


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getImageId() {
        return imageId;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
