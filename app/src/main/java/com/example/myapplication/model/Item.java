package com.example.myapplication.model;

import java.io.Serializable;

public class Item implements Serializable {
    private int id;
    private String name, kh, date, hl, service;

    public Item() {
    }

    public Item(int id, String name, String kh, String date, String hl, String service) {
        this.id = id;
        this.name = name;
        this.kh = kh;
        this.date = date;
        this.hl = hl;
        this.service = service;
    }

    public Item(String name, String kh, String date, String hl, String service) {
        this.name = name;
        this.kh = kh;
        this.date = date;
        this.hl = hl;
        this.service = service;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKh() {
        return kh;
    }

    public void setKh(String kh) {
        this.kh = kh;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHl() {
        return hl;
    }

    public void setHl(String hl) {
        this.hl = hl;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

}
