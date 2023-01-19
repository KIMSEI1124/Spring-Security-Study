package boot.basic.repository;

import boot.basic.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

/**
 * Junit4 : @RunWith(SpringRunner.class)
 * Junit5 : @ExtendWith(SpringExtension.class)
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
/**
 * value = false : DB 초기화를 안함
 * Default 는 true다.
 */
@Rollback(value = false)
class MemberRepositoryTest {
    @Autowired MemberRepository mr;

    @Test
    @Transactional
    @DisplayName("멤버 테스트")
    public void testMember() throws Exception {
        // given
        Member member = new Member();
        member.setName("memberA");

        // when
        Long saveId = mr.save(member);
        Member findMember = mr.findOne(saveId);

        // then
        Assertions
                .assertThat(findMember.getId())
                .isEqualTo(member.getId());
        Assertions
                .assertThat(findMember.getName())
                .isEqualTo(member.getName());
        /**
         * Equals 를 구현을 안했기 때문에 == 비교이다.
         */
        Assertions
                .assertThat(findMember)
                .isEqualTo(member);
        System.out.println("findMember == member : " + (findMember == member));
    }
}