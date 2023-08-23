package com.codestates.member;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.awt.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RestController
//@RequestMapping(value = "/v1/members", produces ={MediaType.APPLICATION_JSON_VALUE})
@RequestMapping(value = "/v1/members")
public class MemberController {
    private final Map<Long, Map<String, Object>> members = new HashMap<>();

    @PostConstruct
    public void init() {
        Map<String, Object> member1 = new HashMap<>();
        long memberId = 1L;
        member1.put("memberId", memberId);
        member1.put("email", "hgd@gmail.com");
        member1.put("name", "홍길동");
        member1.put("phone", "010-1234-5678");

        members.put(memberId, member1);
    }

    //---------------- 여기서 부터 아래에 코드를 구현하세요! --------------------//
    // 1. 회원 정보 수정을 위한 핸들러 메서드 구현
    // 2. 회원 정보 삭제를 위한 핸들러 메서드 구현

    @PostMapping
    public ResponseEntity postMember(@RequestParam Map<String, String> reqParam){
        System.out.println("email : "+reqParam.get("email"));
        System.out.println("id : "+ reqParam.get("memberId"));

        long newMemberId = Long.parseLong(reqParam.get("memberId"));
        Map<String, Object> newMember = new HashMap<>(reqParam);
        members.put(newMemberId, newMember);
        return new ResponseEntity<>(members.get(newMemberId), HttpStatus.OK);
        //return "CREATED";
    }

    @GetMapping("/{member-id}")
    public ResponseEntity getMember(@PathVariable("member-id") long id){
        System.out.println("get member id : "+ id);
        return new ResponseEntity(members.get(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getMembers(){
        System.out.println("get members");
        //return ResponseEntity.ok("get all member");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/{member-id}")
    public ResponseEntity patchMember(
        @PathVariable("member-id") long memberId,
        @RequestParam String phone
    ){
        Map<String, Object> member = members.get(memberId);

        if(member ==null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }else{
            member.put("phone", phone);
        }
        return new ResponseEntity(member, HttpStatus.OK);
    }

    @PutMapping("/{member-id}")
    public ResponseEntity updateMember(@PathVariable("member-id") long id, @RequestParam Map<String, String> reqParam){
        System.out.println("update id : "+ id );
        //Map<String, Object> member = members.get(id);
        members.get(id).replace("phone",reqParam.get("phone"));

        return new ResponseEntity(members.get(id), HttpStatus.OK);
    }
    @DeleteMapping("/{member-id}")
    public ResponseEntity deleteMember(@PathVariable("member-id") long memberId){
        if(members.containsKey(memberId)){
            members.remove(memberId);
        } else{
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
