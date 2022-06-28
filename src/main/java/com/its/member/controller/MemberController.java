package com.its.member.controller;

import com.its.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private  final  MemberService memberService;
// 회원가입
    @GetMapping("/save")
    public String saveForm(){
        return "memberPages/save";
    }


}
