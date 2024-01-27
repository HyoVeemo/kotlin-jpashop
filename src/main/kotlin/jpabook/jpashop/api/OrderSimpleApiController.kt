package jpabook.jpashop.api

import jpabook.jpashop.domain.Order
import jpabook.jpashop.repository.OrderRepository
import jpabook.jpashop.repository.OrderSearch
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController


/**
 * x to One
 * Order
 * Order -> Member
 * Order -> Delivery
 */
@RestController
class OrderSimpleApiController(
    private val orderRepository: OrderRepository
) {

    @GetMapping("/api/v1/simple-orders")
    fun ordersV1(): List<Order> {
        val all = orderRepository.findAll(OrderSearch())

        for (order in all) {
            order.member.name
            order.delivery.address
        }

        return all
    }
}