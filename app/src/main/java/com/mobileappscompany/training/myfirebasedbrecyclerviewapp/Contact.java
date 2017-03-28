package com.mobileappscompany.training.myfirebasedbrecyclerviewapp;

/**
 * Created by User on 2/13/2017.
 */

public class Contact {
    private String key;
    private String name;
    private String phone;

    public Contact() {
    }

    public Contact(String key, String name, String phone) {
        this.key = key;
        this.name = name;
        this.phone = phone;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
