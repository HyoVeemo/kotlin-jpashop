package jpabook.jpashop.domain

import jakarta.persistence.*

@Entity
class OrderItem (
    @Id
    @GeneratedValue
    @Column(name="order_item_id")
    val id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    val item: Item,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    var order: Order,

    val orderPrice: Int,

    val count: Int
){

}