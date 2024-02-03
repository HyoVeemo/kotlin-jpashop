package jpabook.jpashop.repository.order.query

import lombok.Data

@Data
data class OrderItemQueryDto(
    val orderId: Long,
    val itemName: String,
    val orderPrice: Int,
    val count: Int
)
