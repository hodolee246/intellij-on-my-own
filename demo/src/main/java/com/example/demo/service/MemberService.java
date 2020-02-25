package com.example.demo.service;

import com.example.demo.model.Member;
import com.example.demo.repository.MemberRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Optional<Member> findId(String id) {
        return memberRepository.findById(id);
    }

    @Transactional
    public void save(Member member) {
        memberRepository.save(member);
    }

    public Optional<Member> login(String id, String pwd) {
        return memberRepository.findByIdAndPwd(id, pwd);
    }

}
