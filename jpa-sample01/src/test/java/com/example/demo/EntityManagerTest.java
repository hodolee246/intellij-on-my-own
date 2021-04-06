package com.example.demo;

import com.example.demo.domain.Member;
import com.example.demo.domain.Team;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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
        team.setName("teamA");
        em.persist(team);
        // member 저장
        Member member = new Member();
        member.setName("InWoo");
        member.setTeam(team);
        em.persist(member);

        Member findMember = em.find(Member.class, member.getId());
        Team findTeam = findMember.getTeam();

        isTeamAndMember(team, findTeam, member, findMember);
    }

    @Test
    public void jpaTest2() {
        // team 저장
        Team team = new Team();
        team.setName("teamA");
        em.persist(team);
        // member 저장
        Member member = new Member();
        member.setName("InWoo");
        member.setTeam(team);
        em.persist(member);

        Member findMember = em.find(Member.class, member.getId());
        Team findTeam = findMember.getTeam();

        isTeamAndMember(team, findTeam, member, findMember);

        // 새로운 team 추가
        Team team2 = new Team();
        team2.setName("teamB");
        em.persist(team2);
        member.setTeam(team2);
        em.persist(member);

        Member findChangeTeamMember = em.find(Member.class, member.getId());
        Team findChangeTeam = findMember.getTeam();

        isTeamAndMember(team2, findChangeTeam, member, findChangeTeamMember);
    }
}
