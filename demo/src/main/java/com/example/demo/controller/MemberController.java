package com.example.demo.controller;

import com.example.demo.model.Member;
import com.example.demo.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@RestController
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/accountPage/id")
    public ResponseEntity<?> findId(@RequestParam(value = "id") String id) {

        log.info("MemberController findById");

        Optional<Member> member = memberService.findId(id);
        if (member.isPresent()) {
            return ResponseEntity.ok().body(new JSONObject().appendField("result", "true"));
        }
        return ResponseEntity.badRequest().body(new JSONObject().appendField("result", "false"));
    }

    @PostMapping("/accountPage/account")
    public void account(@RequestBody Member member) {

        log.info("MemberController account");

        memberService.save(member);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Member loginMember) {

        log.info("MemberController login");

        Optional<Member> member = memberService.login(loginMember.getId(), loginMember.getPwd());
        if (member.isPresent()) {
            return ResponseEntity.ok().body(new JSONObject().appendField("result", "true"));
        }
        return ResponseEntity.badRequest().body(new JSONObject().appendField("result", "false"));
    }
}
