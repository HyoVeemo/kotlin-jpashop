package jpabook.jpashop.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import org.hibernate.annotations.BatchSize
import java.time.LocalDateTime

@Entity
@Table(name = "orders")
class Order private constructor(
    @Id
    @GeneratedValue
    @Column
    val id: Long = 0,

    @field:JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    var member: Member,

    @field:JsonIgnore
    @BatchSize(size = 100)
    @OneToMany(mappedBy = "order", cascade = [CascadeType.ALL])
    var orderItems: List<OrderItem> = emptyList(),

    @field:JsonIgnore
    @OneToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "delivery_id")
    var delivery: Delivery,

    val orderDate: LocalDateTime = LocalDateTime.now(),
    var status: OrderStatus = OrderStatus.ORDER
) {

    val totalPrice: Int
        get() {
            return orderItems.sumOf { it.totalPrice }
        }

    companion object {
        fun createOrder(member: Member, delivery: Delivery, orderItems: List<OrderItem>): Order {
            val order = Order(member = member, delivery = delivery)
            for (orderItem in orderItems) {
                order.addOrderItem(orderItem)
            }
            return order
        }
    }

    fun addOrderItem(orderItem: OrderItem) {
        orderItems += orderItem
        orderItem.order = this
    }

    fun addDelivery(delivery: Delivery) {
        this.delivery = delivery
        delivery.order = this
    }

    fun cancel() {
        if (delivery.status == DeliveryStatus.COMP) {
            throw IllegalStateException("이미 배송완료 된 상품은 취소가 불가능합니다.")
        }

        this.status = OrderStatus.CANCEL
        for (orderItem in orderItems) {
            orderItem.cancel()
        }
    }

}