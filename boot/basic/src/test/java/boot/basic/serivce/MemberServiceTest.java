package boot.basic.serivce;

import boot.basic.domain.Member;
import boot.basic.repository.MemberRepository;
import boot.basic.service.MemberService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.fail;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class MemberServiceTest {
    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    private final Logger log = LoggerFactory.getLogger(MemberServiceTest.class);

    @Test
    @DisplayName("회원가입")
    @Rollback(value = false)
    public void 회원가입() throws Exception {
        // given
        Member member = Member.builder()
                .name("kim")
                .build();

        // when
        Long saveId = memberService.join(member);

        // then
        Assertions.assertEquals(member, memberRepository.findOne(saveId));
    }

    @Test
    @DisplayName("중복회원예외")
    public void 중복_회원_예외() throws Exception {
        // given
        Member member1 = new Member();
        member1.setName("kim");
        Member member2 = new Member();
        member2.setName("kim");

        // when
        memberService.join(member1);
        try {
            memberService.join(member2); // 예외가 발생해야 한다.
        } catch (Exception e) {
            return;
        }

        // then
        fail("예외가 발생해야 한다.");
    }
}