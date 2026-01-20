package com.carevaluation.prototype.dto;

public class CarForm {
    private String buying;
    private String maint;
    private String doors;
    private String persons;
    private String lugBoot;
    private String safety;

    public String getBuying() { return buying; }
    public void setBuying(String buying) { this.buying = buying; }

    public String getMaint() { return maint; }
    public void setMaint(String maint) { this.maint = maint; }

    public String getDoors() { return doors; }
    public void setDoors(String doors) { this.doors = doors; }

    public String getPersons() { return persons; }
    public void setPersons(String persons) { this.persons = persons; }

    public String getLugBoot() { return lugBoot; }
    public void setLugBoot(String lugBoot) { this.lugBoot = lugBoot; }

    public String getSafety() { return safety; }
    public void setSafety(String safety) { this.safety = safety; }
}