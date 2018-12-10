package com.hotel.Dto;

public class Exposer {
    private boolean exposed;
    private String account;
    private String name;

    public Exposer(boolean exposed){
        this.exposed = exposed;
    }

    public Exposer(boolean exposed,String account,String name){
        this.exposed=exposed;
        this.account=account;
        this.name=name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public boolean isExposed() {
        return exposed;
    }

    public void setExposed(boolean exposed) {
        this.exposed = exposed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
