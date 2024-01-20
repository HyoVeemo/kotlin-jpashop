package jpabook.jpashop.service

import jakarta.persistence.EntityManager
import jpabook.jpashop.domain.Address
import jpabook.jpashop.domain.Book
import jpabook.jpashop.domain.Member
import jpabook.jpashop.domain.OrderStatus
import jpabook.jpashop.exception.NotEnoughStockException
import jpabook.jpashop.repository.OrderRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@Transactional
class OrderServiceTest(
    @Autowired val em: EntityManager,
    @Autowired val orderService: OrderService,
    @Autowired val orderRepository: OrderRepository
) {

    @Test
    fun `상품주문`() {
        // given
        val member = createMember()
        em.persist(member)

        val item = createBook("JPA", 10000, 10)
        em.persist(item)

        val orderCount = 2

        // when
        val orderId = orderService.order(member.id, item.id, orderCount)

        // then
        val getOrder = orderRepository.findOne(orderId)

        assertEquals(OrderStatus.ORDER, getOrder.status, "상품 주문 시 상태는 ORDER")
        assertEquals(1, getOrder.orderItems.size, "주문한 상품 종류 수가 정확해야 한다 ")
        assertEquals(10000 * orderCount, getOrder.totalPrice, "주문 가격은 가격 * 수량이다.")
        assertEquals(8, item.stockQuantity, "주문 수량만큼 재고가 감소해야 한다. ")
    }

    @Test
    fun `주문_취소`() {
        // given

        // given
        val member = createMember()
        val item = createBook("시골 JPA", 10000, 10)

        val orderCount = 2

        val orderId = orderService.order(member.id, item.id, orderCount)

        // when
        orderService.cancelOrder(orderId)

        // then
        val getOrder = orderRepository.findOne(orderId)

        assertEquals(OrderStatus.CANCEL, getOrder.status, "주문 취소시 상태는 CANCEL 이다. ")
        assertEquals(10, item.stockQuantity, "주문이 취소된 상품은 그만큼 재가 증가해야 한다. ")
    }

    @Test
    fun `상품주문_재고수량초과`() {
        // given
        val member = createMember()
        val item = createBook("시골 JPA", 10000, 10)


        val orderCount = 11

        assertThrows(NotEnoughStockException::class.java) { orderService.order(member.id, item.id, orderCount) }
    }

    private fun createBook(name: String, price: Int, stockQuantity: Int): Book {
        val book = Book(name = name, price = price, stockQuantity = stockQuantity, author = "김영한")
        em.persist(book)
        return book
    }

    private fun createMember(): Member {
        val member = Member(name = "kim", address = Address("", "", ""))
        em.persist(member)
        return member
    }
}