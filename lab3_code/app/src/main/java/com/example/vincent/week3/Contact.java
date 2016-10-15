package com.example.vincent.week3;

/**
 * Created by Vincent on 2016/10/11.
 */

public class Contact {
    private String name;
    private String phone;
    private String phone_add;
    private String type;
    private String bg_color;

    public Contact(String name, String phone, String phone_add, String type, String bg_color) {
        this.name = name;
        this.phone = phone;
        this.phone_add = phone_add;
        this.type = type;
        this.bg_color = bg_color;
    }

    public String getName() {
        return this.name;
    }

    public String getPhone() {
        return this.phone;
    }

    public String getPhone_add() {
        return this.phone_add;
    }

    public String getType() {
        return this.type;
    }

    public String getBg_color() {
        return this.bg_color;
    }
}
