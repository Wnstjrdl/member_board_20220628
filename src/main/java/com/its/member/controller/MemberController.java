package com.its.member.controller;

import com.its.member.dto.MemberDTO;
import com.its.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;

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
    //회원가입처리
    @PostMapping("/save")
    public String save(@ModelAttribute MemberDTO memberDTO) throws IOException {
        memberService.save(memberDTO);
        return  "memberPages/login";
    }
    //로그인 화면
    @GetMapping("/login")
    public String loginForm(@RequestParam(value = "redirectURL", defaultValue = "/board/paging")String redirectURL, Model model){
        model.addAttribute("redirectURL",redirectURL);
        return  "memberPages/login";
    }
    //로그인처리
    @PostMapping("/login")
    public String login(@ModelAttribute MemberDTO memberDTO,Model model, HttpSession session){
        MemberDTO loginResult=memberService.login(memberDTO);
        if(loginResult != null){
            model.addAttribute("loginResult",loginResult);
            session.setAttribute("loginEmail", loginResult.getMemberEmail());
            session.setAttribute("id",loginResult.getId());
            return  "redirect:board/paging";
        }else {
            return "memberPages/login";

        }

    }
    //중복체크
    @PostMapping("/EmailCheck")
    public @ResponseBody String EmailCheck(@RequestParam String memberEmail){
     String checkResult= memberService.EmailCheck(memberEmail);
     return checkResult;
    }

}
