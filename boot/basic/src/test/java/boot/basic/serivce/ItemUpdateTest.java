package boot.basic.serivce;

import boot.basic.domain.item.Book;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ItemUpdateTest {
    @PersistenceContext
    EntityManager em;

    @Test
    public void updateTest() {
        // given
        Book book = em.find(Book.class, 1L);

        // when
        book.setName("test");

        // 변경 감지 == dirty Checking
        // then


    }
}
