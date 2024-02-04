package jpabook.jpashop.service.query

import jpabook.jpashop.domain.OrderItem

class OrderItemDto(oi: OrderItem) {
    val name = oi.item.name
    val orderPrice = oi.orderPrice
    val count = oi.count
}
