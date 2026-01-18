/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import java.util.Optional;

/**
 *
 * @author An
 */
public class UpdateDto {
    public Optional<String> name = Optional.empty();
    public Optional<Integer> age = Optional.empty();
    public Optional<String> gender = Optional.empty();
    public Optional<Double> lessMoney = Optional.empty();

    // Constructor rỗng
    public UpdateDto() {
    }

    // Constructor đầy đủ (giữ lại để tương thích)
    public UpdateDto(Optional<String> name, Optional<Integer> age, Optional<String> gender,
            Optional<Double> lessMoney) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.lessMoney = lessMoney;
    }

    // Helper methods - nhận String từ request.getParameter()
    public void setNameIfPresent(String value) {
        if (value != null && !value.trim().isEmpty()) {
            this.name = Optional.of(value.trim());
        }
    }

    public void setAgeIfPresent(String value) {
        if (value != null && !value.trim().isEmpty()) {
            this.age = Optional.of(Integer.parseInt(value.trim()));
        }
    }

    public void setGenderIfPresent(String value) {
        if (value != null && !value.trim().isEmpty()) {
            this.gender = Optional.of(value.trim());
        }
    }

    public void setLessMoneyIfPresent(String value) {
        if (value != null && !value.trim().isEmpty()) {
            this.lessMoney = Optional.of(Double.parseDouble(value.trim()));
        }
    }
}
