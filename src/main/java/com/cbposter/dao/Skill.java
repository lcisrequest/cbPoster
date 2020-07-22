package com.cbposter.dao;

import javax.persistence.*;

/**
 * @Auther: lc
 * @Date: 2020/7/16 11:10
 */
@Entity
@Table(name = "skill")
public class Skill {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "additionaldamage")
    private String additionalDamage;

    @Column(name = "hitstimes")
    private String hitsTimes;

    @Column(name = "type")
    private String type;

    @Column(name = "speed")
    private int speed;

    @Column(name = "name")
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

    public String getAdditionalDamage() {
        return additionalDamage;
    }

    public void setAdditionalDamage(String additionalDamage) {
        this.additionalDamage = additionalDamage;
    }

    public String getHitsTimes() {
        return hitsTimes;
    }

    public void setHitsTimes(String hitsTimes) {
        this.hitsTimes = hitsTimes;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
