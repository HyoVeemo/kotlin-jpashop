package jpabook.jpashop.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*

@Entity
class Delivery(
    @Id
    @GeneratedValue
    @Column(name = "delivery_id")
    var id: Long = 0,

    @Enumerated(EnumType.STRING)
    var status: DeliveryStatus = DeliveryStatus.PEINDING,

    @Embedded
    var address: Address,

    ) {
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    var order: Order? = null
}