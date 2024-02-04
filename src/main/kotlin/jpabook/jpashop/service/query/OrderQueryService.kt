package jpabook.jpashop.service.query

import jpabook.jpashop.repository.OrderRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class OrderQueryService(
    private val orderRepository: OrderRepository
) {
    fun ordersV3(): List<OrderDto> {
        val orders = orderRepository.findWithItem()
        return orders.map {
            OrderDto(it)
        }
    }
}