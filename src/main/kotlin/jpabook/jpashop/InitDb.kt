package jpabook.jpashop

import jakarta.annotation.PostConstruct
import jakarta.persistence.EntityManager
import jpabook.jpashop.domain.*
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional


@Component
class InitDb(private val initService: InitService) {

    @PostConstruct
    fun init() {
        initService.dbInit1()
        initService.dbInit2()
    }

    companion object {
        @Component
        @Transactional
        class InitService(val em: EntityManager) {
            fun dbInit1() {
                val member = createMember(name = "A")
                em.persist(member)

                val book1 = createBook(name = "JPA Book1", price = 1000, stockQuantity = 100)
                em.persist(book1)

                val book2 = createBook(name = "JPA Book2", price = 20000, stockQuantity = 100)
                em.persist(book2)

                val orderItem1 = OrderItem.createOrderItem(book1, 10000, 1)
                val orderItem2 = OrderItem.createOrderItem(book2, 20000, 2)

                val delivery = createDelivery(member)
                val order =
                    Order.createOrder(member = member, delivery = delivery, orderItems = listOf(orderItem1, orderItem2))
                em.persist(order)
            }

            fun dbInit2() {
                val member = createMember("B")
                em.persist(member)

                val book1 = createBook(name = "SQL Book1", price = 10000, stockQuantity = 100)
                em.persist(book1)

                val book2 = createBook(name = "SQL Book2", price = 20000, stockQuantity = 100)
                em.persist(book2)

                val orderItem1 = OrderItem.createOrderItem(book1, 10000, 1)
                val orderItem2 = OrderItem.createOrderItem(book2, 20000, 2)

                val delivery = createDelivery(member)
                val order =
                    Order.createOrder(member = member, delivery = delivery, orderItems = listOf(orderItem1, orderItem2))
                em.persist(order)
            }

            private fun createDelivery(member: Member): Delivery {
                val delivery = Delivery(address = member.address!!)
                return delivery
            }

            private fun createBook(name: String, price: Int, stockQuantity: Int): Book {
                return Book(name = name, price = price, stockQuantity = stockQuantity)
            }

            private fun createMember(name: String): Member {
                val member = Member(name = name, address = Address("서울", "1", "1"))
                return member
            }
        }

        private fun member(): Member {
            val member = Member(name = "userA", address = Address("서울시", "123길", "04321"))
            return member
        }
    }
}