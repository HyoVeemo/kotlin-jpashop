package jpabook.jpashop.api

import jpabook.jpashop.domain.Order
import jpabook.jpashop.domain.OrderItem
import jpabook.jpashop.repository.OrderRepository
import jpabook.jpashop.repository.OrderRepositoryImpl
import jpabook.jpashop.repository.OrderSearch
import jpabook.jpashop.repository.order.query.OrderItemQueryDto
import jpabook.jpashop.repository.order.query.OrderQueryDto
import jpabook.jpashop.repository.order.query.OrderQueryRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class OrderApiController(
    private val orderRepository: OrderRepository,
    private val orderQueryRepository: OrderQueryRepository,
    private val orderRepositoryImpl: OrderRepositoryImpl
) {

    @GetMapping("/api/v1/orders")
    fun ordersV1(): List<Order> {
        val all = orderRepositoryImpl.findAll()
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

    @GetMapping("/api/v3/orders")
    fun ordersV3(): List<OrderDto> {
        val orders = orderRepository.findWithItem()
        return orders.map {
            OrderDto(it)
        }
    }

    @GetMapping("/api/v3.1/orders")
    fun ordersV3_page(
        @RequestParam(value = "offset", defaultValue = "0") offset: Int,
        @RequestParam(value = "limit", defaultValue = "100") limit: Int
    ): List<OrderDto> {
        val orders = orderRepository.findAllWithMemberDelivery(offset, limit)
        return orders.map {
            OrderDto(it)
        }
    }

    @GetMapping("/api/v4/orders")
    fun ordersV4(): List<OrderQueryDto> {
        return orderQueryRepository.findOrderQueryDtos()
    }

    @GetMapping("/api/v5/orders")
    fun ordersV5(): List<OrderQueryDto> {
        return orderQueryRepository.findAllByDto_optimization()
    }

    @GetMapping("/api/v6/orders")
    fun ordersV6(): List<OrderQueryDto> {
        val flats = orderQueryRepository.findAllByDto_flat()
        return flats.groupBy { OrderQueryDto(it.orderId, it.name, it.orderDate, it.orderStatus, it.address) }
            .entries.map {
                OrderQueryDto(
                    it.key.orderId,
                    it.key.name,
                    it.key.orderDate,
                    it.key.orderStatus,
                    it.key.address,
                    it.value.map { f ->
                        OrderItemQueryDto(it.key.orderId, f.name, f.orderPrice, f.count)
                    }
                )
            }

    }

    companion object {

        class OrderDto(o: Order) {
            val orderItems = o.orderItems // proxy 를 초기화 안했는데 item 이 다 표시되는 이유는..?
            val orderId = o.id
            val name = o.member.name
            val orderDate = o.orderDate
            val address = o.delivery.address
        }

        class OrderItemDto(oi: OrderItem) {
            val name = oi.item.name
            val orderPrice = oi.orderPrice
            val count = oi.count
        }

    }
}