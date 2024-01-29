package jpabook.jpashop.api

import jpabook.jpashop.domain.Order
import jpabook.jpashop.repository.OrderRepository
import jpabook.jpashop.repository.OrderSearch
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class OrderApiController(
    private val orderRepository: OrderRepository,
) {

    @GetMapping("/api/v1/orders")
    fun ordersV1(): List<Order> {
        // todo: 프록시 초기화 하면 hibernate5 모듈에서 초기화된 필드를 반환해야 하는데 필드가 없는 문제 있음
        val all = orderRepository.findAll(OrderSearch())
        all.forEach { it ->
            it.member.name
            it.delivery.address
            it.orderItems.forEach { orderItem ->
                orderItem.item.name
            }
        }
        return all
    }
}