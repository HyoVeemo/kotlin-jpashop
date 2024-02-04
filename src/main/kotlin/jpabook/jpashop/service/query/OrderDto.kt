package jpabook.jpashop.service.query

import jpabook.jpashop.domain.Order

class OrderDto(o: Order) {
    val orderItems = o.orderItems // proxy 를 초기화 안했는데 item 이 다 표시되는 이유는..?
    val orderId = o.id
    val name = o.member.name
    val orderDate = o.orderDate
    val address = o.delivery.address
}

