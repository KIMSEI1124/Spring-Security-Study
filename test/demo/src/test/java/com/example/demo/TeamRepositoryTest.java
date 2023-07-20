package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class TeamRepositoryTest {

    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private RelatedRepository relatedRepository;

    @Test
    void 테스트() {
        Team team1 = new Team("1팀");
        Team team2 = new Team("2팀");


        Member 김 = new Member("김");
        Member 이 = new Member("이");
        Member 박 = new Member("박");

        Related team1_김 = new Related();
        Related team2_김 = new Related();
        Related team1_이 = new Related();
        Related team2_박 = new Related();

        team1_김.changeTeam(team1);
        team1_김.changeMember(김);
        team2_김.changeTeam(team2);
        team2_김.changeMember(김);
        team1_이.changeTeam(team1);
        team1_이.changeMember(이);
        team2_박.changeTeam(team2);
        team2_박.changeMember(박);

        relatedRepository.save(team1_김);
        relatedRepository.save(team2_김);
        relatedRepository.save(team1_이);
        relatedRepository.save(team2_박);

        teamRepository.save(team1);
        teamRepository.save(team2);
        memberRepository.save(김);
        memberRepository.save(이);
        memberRepository.save(박);

        for (Team team : teamRepository.findAll()) {
            System.out.println("team : " + team.getName());
        }

        for (Related related : relatedRepository.findAll()) {
            System.out.println("related = " + related.getId());
        }

        for (Member member : memberRepository.findAll()) {
            System.out.println("member : " + member.getName() + ", size : " + member.getRelated().size());
        }

        System.out.println("================ 삭제 후 ================");

        teamRepository.delete(team1);

        teamRepository.flush();

        for (Team team : teamRepository.findAll()) {
            System.out.println("team : " + team.getName());
        }

        for (Related related : relatedRepository.findAll()) {
            System.out.println("related = " + related.getId());
        }

        for (Member member : memberRepository.findAll()) {

            System.out.println("member : " + member.getName());

            for (Related related : member.getRelated()) {
                System.out.println("related = " + related.getId());
            }
        }
    }
}