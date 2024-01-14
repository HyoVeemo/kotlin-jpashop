package jpabook.jpashop.domain

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "orders")
class Order (
    @Id
    @GeneratedValue
    @Column
    private val id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    var member: Member,

    @OneToMany(mappedBy = "order", cascade = [CascadeType.ALL])
    var orderItems: List<OrderItem> = emptyList<OrderItem>(),

    val orderDate: LocalDateTime,

    var status: OrderStatus
){
    @OneToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "delivery_id")
    var delivery: Delivery?  = null

    // 연관관계 메서드
    fun addOrderItem(orderItem: OrderItem){
        orderItems += orderItem
        orderItem.order = this
    }

    fun setDelivery(delivery: Delivery){
        this.delivery = delivery
        delivery.order = this
    }
}