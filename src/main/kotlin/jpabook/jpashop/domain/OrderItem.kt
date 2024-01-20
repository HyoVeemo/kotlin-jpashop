package jpabook.jpashop.domain

import jakarta.persistence.*

@Entity
class OrderItem private constructor(
    @Id
    @GeneratedValue
    @Column(name = "order_item_id")
    val id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    val item: Item,

    val orderPrice: Int,

    val count: Int
) {
    companion object {
        fun createOrderItem(item: Item, orderPrice: Int, count: Int): OrderItem {
            val orderItem = OrderItem(item = item, orderPrice = orderPrice, count = count)
            item.removeStock(count)
            return orderItem
        }
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    var order: Order? = null

    fun cancel() {
        item.addStock(count)
    }

    val totalPrice: Int
        get() {
            return this.orderPrice * this.count
        }
}