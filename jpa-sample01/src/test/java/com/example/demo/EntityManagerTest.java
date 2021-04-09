package com.example.demo;

import com.example.demo.domain.Member;
import com.example.demo.domain.Team;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class EntityManagerTest {

    EntityManagerFactory emf;
    EntityManager em;

    @BeforeEach
    public void setUp() {
        emf = Persistence.createEntityManagerFactory("jpaSample01");
        em = emf.createEntityManager();
    }

    public void isTeamAndMember(Team t1, Team t2, Member m1, Member m2) {
        Assertions.assertEquals(t1, t2);
        Assertions.assertEquals(m1, m2);
    }

    @Test
    public void jpaTest1() {
        // team 저장
        Team team = new Team();
        team.setId("teamId");
        team.setName("teamName");
        // member 저장 & member 에만 Team 등록
        Member member = new Member();
        member.setId("InWoo");
        member.setName("JeonInWoo");
        member.setTeam(team);

        List<Member> members = team.getMembers();
        Assertions.assertEquals(members.size(), 0); // team 에는 member 값이 없음
    }

    @Test
    public void jpaTest2() {
        // team 저장
        Team team = new Team();
        team.setId("teamId");
        team.setName("teamName");
        // member 저장
        Member member = new Member();
        member.setId("InWoo");
        member.setName("JeonInWoo");
        member.setTeam(team);
        // member -> team, team -> member 등록
        member.setTeam(team);
        team.getMembers().add(member);

        List<Member> members = team.getMembers();
        Assertions.assertEquals(members.size(), 1);
    }

}
