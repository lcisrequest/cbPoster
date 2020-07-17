package com.cbposter.dao;

import java.util.List;

/**
 * @Auther: lc
 * @Date: 2020/7/9 16:38
 */
public class Role {

    private int id;
    //姓名
    private String name;
    //身份
    private String identity;
    //相貌
    private String looks;
    //钱
    private double money;
    //攻击力
    private double attack;
    //防御力
    private double defense;
    //血量
    private double blood;


    //物品
    private List<Goods> goods;
    //技能
    private List<Skill> skills;

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getLooks() {
        return looks;
    }

    public void setLooks(String looks) {
        this.looks = looks;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
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

    public double getAttack() {
        return attack;
    }

    public void setAttack(double attack) {
        this.attack = attack;
    }

    public double getDefense() {
        return defense;
    }

    public void setDefense(double defense) {
        this.defense = defense;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public double getBlood() {
        return blood;
    }

    public void setBlood(double blood) {
        this.blood = blood;
    }

    public List<Goods> getGoods() {
        return goods;
    }

    public void setGoods(List<Goods> goods) {
        this.goods = goods;
    }
}
