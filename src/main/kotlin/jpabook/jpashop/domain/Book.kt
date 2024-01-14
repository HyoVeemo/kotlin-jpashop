package jpabook.jpashop.domain

import jakarta.persistence.Entity

@Entity
class Book (
    id: Long,
    name: String,
    price: Int,
    stockQuantity: Int,
    val author: String
): Item(id, name, price, stockQuantity) {
}
