package com.cbposter.dao;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @Auther: lc
 * @Date: 2020/7/16 11:10
 */
@Entity
@Table(name = "skill")
public class Skill {
    private int id;
    private String name;
    private double additionalDamage;
    private double hitsTimes;
    private int type;
    private int speed;
    private String specialEffects;

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public String getSpecialEffects() {
        return specialEffects;
    }

    public void setSpecialEffects(String specialEffects) {
        this.specialEffects = specialEffects;
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

    public double getAdditionalDamage() {
        return additionalDamage;
    }

    public void setAdditionalDamage(double additionalDamage) {
        this.additionalDamage = additionalDamage;
    }

    public double getHitsTimes() {
        return hitsTimes;
    }

    public void setHitsTimes(double hitsTimes) {
        this.hitsTimes = hitsTimes;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
