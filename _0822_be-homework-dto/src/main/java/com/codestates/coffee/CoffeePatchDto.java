package com.codestates.coffee;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

public class CoffeePatchDto {
    private long coffeeId;
    @Pattern(regexp= "^\\S+(\\s?\\S+)*$",message = "입력했으면 공백불가")
    private String korName;
    @Pattern(regexp= "^[a-zA-Z]+(\\s?[a-zA-Z]+)*$",message = "영문만가능, 가운데 스페이스 가능")
    private String engName;
    @Min(100)
    @Max(50000)
    private int Price;

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
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public long getCoffeeId() {
        return coffeeId;
    }

    public void setCoffeeId(long coffeeId) {
        this.coffeeId = coffeeId;
    }
}
