package boot.basic.serivce;

import boot.basic.domain.Address;
import boot.basic.domain.Member;
import boot.basic.domain.Order;
import boot.basic.domain.OrderStatus;
import boot.basic.domain.item.Book;
import boot.basic.domain.item.Item;
import boot.basic.repository.OrderRepository;
import boot.basic.service.OrderService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class OrderServiceTest {

    @PersistenceContext
    EntityManager em;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderRepository orderRepository;

    @Test
    public void 상품주문() {
        // given
        Member member = new Member();
        member.setName("회원1");
        member.setAddress(new Address("서울", "강가", "123-123"));
        em.persist(member);

        Item book = new Book();
        book.setName("시골 JPA");
        book.setPrice(10000);
        book.setStockQuantity(10);
        em.persist(book);

        int orderCount = 2;

        // when
        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);
        System.out.println("orderId = " + orderId);

        // then
        Order getOrder = orderRepository.findOne(orderId);
        System.out.println("getOrderId = " + getOrder);
        Assertions.assertEquals(OrderStatus.ORDER, getOrder.getStatus(), "상품 주문시 상태는 ORDER");
    }

    @Test
    public void 주문취소() {
        // given

        // when

        // then
    }

    @Test
    public void 상품주문_재고수량초과() {
        // given

        // when

        // then
    }
}