package jpabook.jpashop.domain

import jakarta.persistence.Entity

@Entity
class Book(
    name: String,
    price: Int,
    stockQuantity: Int,
    val author: String = ""
) : Item(name = name, price = price, stockQuantity = stockQuantity) {
}
