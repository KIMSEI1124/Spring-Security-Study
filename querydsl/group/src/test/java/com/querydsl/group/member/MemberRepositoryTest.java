package com.querydsl.group.member;

import com.querydsl.group.team.Team;
import com.querydsl.group.team.TeamRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class MemberRepositoryTest {

    private static Member member1;
    private static Member member2;
    private static Member member3;
    private static Team team1;
    private static Team team2;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private TeamRepository teamRepository;

    @BeforeEach
    void setUp() {
        team1 = Team.builder()
                .name("조선")
                .build();
        team2 = Team.builder()
                .name("고구려")
                .build();

        member1 = Member.builder()
                .name("이순신")
                .build();
        member2 = Member.builder()
                .name("이성계")
                .build();
        member3 = Member.builder()
                .name("주몽")
                .build();

        team1.addMember(member1);
        team1.addMember(member2);
        team2.addMember(member3);

        teamRepository.save(team1);
        teamRepository.save(team2);

        memberRepository.save(member1);
        memberRepository.save(member2);
        memberRepository.save(member3);
    }

    @ParameterizedTest
    @ValueSource(ints = {3})
    void findMembersTest(int size) {
        List<Member> members = memberRepository.findMembers();

        assertThat(members).hasSize(size);
    }

    @Test
    void findByMemberIdTest() {
        Member findMember = memberRepository.findByMemberId(member1.getId());

        assertThat(member1).isEqualTo(findMember);
    }

    @ParameterizedTest
    @ValueSource(ints = {1})
    void findByTeamNameTest(int size) {
        List<Member> members = memberRepository.findByTeamName("고구려");

        assertThat(members).hasSize(size);
    }

    @ParameterizedTest
    @ValueSource(ints = {1})
    void findByTeamNameTestV2(int size) {
        List<Member> members = memberRepository.findByTeamNameV2("고구려");

        assertThat(members).hasSize(size);
    }

    @ParameterizedTest
    @ValueSource(ints = {3})
    void findByTeamNameTestV2Null(int size) {
        List<Member> members = memberRepository.findByTeamNameV2(null);

        assertThat(members).hasSize(size);
    }
}