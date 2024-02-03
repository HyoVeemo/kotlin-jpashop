package jpabook.jpashop.repository.order.query

import jpabook.jpashop.domain.Address
import jpabook.jpashop.domain.OrderStatus
import lombok.Data
import java.time.LocalDateTime

@Data
class OrderQueryDto(
    val orderId: Long,
    val name: String,
    val orderDate: LocalDateTime,
    val orderStatus: OrderStatus,
    val address: Address,
) {
    var orderItems: List<OrderItemQueryDto> = listOf()
}