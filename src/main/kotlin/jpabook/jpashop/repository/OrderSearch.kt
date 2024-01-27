package jpabook.jpashop.repository

import jpabook.jpashop.domain.OrderStatus

data class OrderSearch(
    val memberName: String? = null,
    val orderStatus: OrderStatus? = null
)