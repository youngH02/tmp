package com.codestates.coffee;

import javax.validation.constraints.NotBlank;

public class CoffeePostDto {
    @NotBlank
    private String korName;
    @NotBlank
    private String engName;
    @NotBlank
    private int price;

}
