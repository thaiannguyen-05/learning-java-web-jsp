/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author An
 */
public class KhachHang {
    private int id;
        private String name;
        private int age;
        private String gender;
        private Double lessMoney;

    public KhachHang(int id, String name, int age, String gender, Double lessMoney) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.lessMoney = lessMoney;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Double getLessMoney() {
        return lessMoney;
    }

    public void setLessMoney(Double lessMoney) {
        this.lessMoney = lessMoney;
    }

    
    
    
}
