package jpabook.jpashop.api

import jpabook.jpashop.domain.Address
import jpabook.jpashop.domain.Order
import jpabook.jpashop.domain.OrderStatus
import jpabook.jpashop.repository.OrderRepository
import jpabook.jpashop.repository.OrderSearch
import jpabook.jpashop.repository.order.simplequery.OrderSimpleQueryDto
import jpabook.jpashop.repository.order.simplequery.OrderSimpleQueryRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime


/**
 * x to One
 * Order
 * Order -> Member
 * Order -> Delivery
 */
@RestController
class OrderSimpleApiController(
    private val orderRepository: OrderRepository,
    private val orderSimpleQueryRepository: OrderSimpleQueryRepository
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


    @GetMapping("/api/v2/simple-orders")
    fun ordersV2(): List<SimpleOrderDto> {
        val orders = orderRepository.findAll(OrderSearch())
        return orders.map {
            SimpleOrderDto(it)
        }
    }

    @GetMapping("/api/v3/simple-orders")
    fun ordersV3(): List<SimpleOrderDto> {
        val orders = orderRepository.findAllWithMemberDelivery()
        return orders.map {
            SimpleOrderDto(it)
        }
    }

    @GetMapping("/api/v4/simple-orders")
    fun ordersV4(): List<OrderSimpleQueryDto> {
        return orderSimpleQueryRepository.findOrderDtos()
    }

    companion object {
        data class SimpleOrderDto(
            private val order: Order
        ) {
            val orderId: Long = order.id;
            val name: String = order.member.name;
            val orderDate: LocalDateTime = order.orderDate;
            val orderStatus: OrderStatus = order.status;
            val address: Address = order.delivery.address;
        }
    }
}

