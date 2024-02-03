package jpabook.jpashop.api

import jpabook.jpashop.domain.Order
import jpabook.jpashop.domain.OrderItem
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
//        all.forEach { it ->
//            it.member.name
//            it.delivery.address
//            it.orderItems.forEach { orderItem ->
//                orderItem.item.name
//            }
//        }
        return all
    }

    @GetMapping("/api/v2/orders")
    fun ordersV2(): List<OrderDto> {
        val orders = orderRepository.findAll(OrderSearch())
        return orders.map {
            OrderDto(it)
        }
    }

    companion object {
        class OrderDto(o: Order) {
            val orderItems = o.orderItems // proxy 를 초기화 안했는데 item 이 다 표시되는 이유는..?
            val orderId = o.id
            val name = o.member.name
            val orderDate = o.orderDate
            val address = o.delivery.address
//            val orderItems = o.orderItems.map {
//                OrderItemDto(it)
//            }
        }

        class OrderItemDto(oi: OrderItem) {
            val name = oi.item.name
            val orderPrice = oi.orderPrice
            val count = oi.count
        }

    }
}