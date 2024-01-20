package jpabook.jpashop.domain

import jakarta.persistence.*

@Entity
class Delivery(
    @Id
    @GeneratedValue
    @Column(name = "delivery_id")
    var id: Long = 0,

    var status: DeliveryStatus = DeliveryStatus.PEINDING,

    var address: Address,

    ) {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    var order: Order? = null
}