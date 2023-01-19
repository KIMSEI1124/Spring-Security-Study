package com.study.querydsl.repository;

import com.study.querydsl.dto.MemberSearchCondition;
import com.study.querydsl.dto.MemberTeamDto;
import com.study.querydsl.entity.Member;
import com.study.querydsl.entity.Team;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
class MemberJPARepositoryTest {

    @PersistenceContext
    EntityManager em;

    @PersistenceContext
    MemberJPARepository memberJPARepository;

    @Test
    public void basicTest() {
        // given
        Member member = new Member("member1", 10);

        // when
        memberJPARepository.save(member);
        Member findMember = memberJPARepository.findById(member.getId()).get();

        List<Member> result1 = memberJPARepository.findAll();

        List<Member> result2 = memberJPARepository.findByUsername("member1");

        // then
        Assertions.assertEquals(findMember, member);
        Assertions.assertTrue(result1.contains(member));
        Assertions.assertTrue(result2.contains(member));
    }

    @Test
    public void basicQuerydslTest() {
        // given
        Member member1 = new Member("member1", 10);

        // when
        memberJPARepository.save(member1);

        Optional<Member> findByMember = memberJPARepository.findById(member1.getId());
        if (findByMember.isPresent()) {
            Member findMember = findByMember.get();
        }

        List<Member> result1 = memberJPARepository.findAll_Querydsl();

        List<Member> result2 = memberJPARepository.findByUsername_Querydsl("member1");

        // then
        Assertions.assertTrue(result1.contains(member1));
        Assertions.assertTrue(result2.contains(member1));
    }

    @Test
    public void searchTest() {
        // given
        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");

        em.persist(teamA);
        em.persist(teamB);

        Member member1 = new Member("member1", 10, teamA);
        Member member2 = new Member("member2", 20, teamA);
        Member member3 = new Member("member3", 30, teamB);
        Member member4 = new Member("member4", 40, teamB);

        em.persist(member1);
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);


        // when
        MemberSearchCondition condition = new MemberSearchCondition();
        condition.setAgeGoe(35);
        condition.setAgeLoe(40);
        condition.setTeamName("teamB");

        List<MemberTeamDto> result = memberJPARepository.search(condition);

        // then
        assertThat(result).extracting("username").containsExactly("member4");
    }
}