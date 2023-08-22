package com.codestates.coffee;

import javax.validation.constraints.*;

public class CoffeePostDto {
    @NotBlank(message = "공백불가")
    private String korName;
    @NotBlank
    @Pattern(regexp= "^[a-zA-Z]+(\\s?[a-zA-Z]+)*$",message = "영문만가능, 가운데 스페이스 가능")
    private String engName;
    @Min(100)
    @Max(50000)
    @NotNull
    private int price;

    public String getKorName() {
        return korName;
    }

    public void setKorName(String korName) {
        this.korName = korName;
    }

    public String getEngName() {
        return engName;
    }

    public void setEngName(String engName) {
        this.engName = engName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
