package jpabook.jpashop.domain

import jakarta.persistence.*
import jpabook.jpashop.exception.NotEnoughStockException

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
abstract class Item(
    @Id
    @GeneratedValue
    @Column(name = "item_id")
    val id: Long = 0,

    var name: String,

    var price: Int = 0,

    var stockQuantity: Int = 0,
) {
    @ManyToMany
    val categories: List<Category> = emptyList()

    // -- 비즈니스 로직 -- //

    /**
     * stock 증가
     */
    fun addStock(quantity: Int) {
        this.stockQuantity += quantity
    }

    /**
     * stock 감소
     */
    fun removeStock(quantity: Int) {
        if (this.stockQuantity - quantity < 0) {
            throw NotEnoughStockException("need more stock")
        }

        this.stockQuantity -= quantity
    }

}