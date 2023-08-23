package com.codestates.coffee;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1/coffees")
public class CoffeeController {
    private final Map<Long, Map<String, Object>> coffees = new HashMap<>();

    @PostConstruct
    public void init() {
        Map<String, Object> coffee1 = new HashMap<>();
        long coffeeId = 1L;
        coffee1.put("coffeeId", coffeeId);
        coffee1.put("korName", "바닐라 라떼");
        coffee1.put("engName", "Vanilla Latte");
        coffee1.put("price", 4500);

        coffees.put(coffeeId, coffee1);
    }

    //---------------- 여기서 부터 아래에 코드를 구현하세요! -------------------//
    // 1. 커피 정보 수정을 위한 핸들러 메서드 구현
    // 2. 커피 정보 삭제를 위한 핸들러 서드 구현

    /*
     coffeeId에 해당하는 커피 정보가 coffees에 존재하지 않으면 404 NOT found HTTP Status 전송
     coffeeId에 해당하는 커피 정보가 Map coffees에 존재한다면 커피 정보를 받아온 파라미터 값으로 수정한다.
     */
    @PatchMapping("/{coffee-id}")
    public ResponseEntity patchCoffee(
            @PathVariable("coffee-id") long coffeeId,
            @RequestParam("korName") String korName,
            @RequestParam("price") int price
    ){
        Map<String, Object> coffee = coffees.get(coffeeId);
        if(coffee == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            coffee.put("korname", korName);
            coffee.put("price", price);
        }
        return new ResponseEntity(coffee, HttpStatus.OK);
    }

    @PutMapping("/{coffee-id}")
    public ResponseEntity updateCoffee(@PathVariable("coffee-id")long coffeeId, @RequestParam Map<String, String> reqParam){
        Map<String, Object> coffee = coffees.get(coffeeId);
        coffee.replace("korName",reqParam.get("korName"));
        coffee.replace("price",reqParam.get("price"));
        System.out.println(coffee.get("korName"));

        return new ResponseEntity<>(coffees.get(coffeeId), HttpStatus.OK); // http status 200 (정상)
    }

    /*
    커피 정보 삭제를 위핸 핸들러 메서드
    - 커피 정보가 map coffees에 없다면 not found status 전달
    - 커피 정보가 있다면 remove로 map에서 삭제하고, no_content status 전달
     */
    @DeleteMapping("/{coffee-id}")
    public ResponseEntity deleteCoffee(@PathVariable("coffee-id") long coffeeId){
        if(coffees.containsKey(coffeeId)){
            coffees.remove(coffeeId);
        }else{
            return new ResponseEntity(HttpStatus.NOT_FOUND); // Http status 404
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT); // Http status 204
    }
    // http status 500 : 서비스 내부 error

//    @DeleteMapping("/{coffee-id}")
//    public ResponseEntity deleteCoffee(@PathVariable("coffee-id")long coffeeId){
//        //coffees.remove(coffeeId);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }



}
