package jpabook.jpashop.domain

import jakarta.persistence.*

@Entity
class Delivery (
    @Id
    @GeneratedValue
    @Column(name="delivery_id")
    val id: Long,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    var order: Order,
)