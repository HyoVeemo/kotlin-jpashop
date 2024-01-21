package jpabook.jpashop.domain

import jakarta.persistence.DiscriminatorValue
import jakarta.persistence.Entity

@Entity
@DiscriminatorValue("B")
class Book(
    id: Long = 0L,
    name: String,
    price: Int,
    stockQuantity: Int,
    val author: String = "",
    val isbn: String = ""
) : Item(id = id, name = name, price = price, stockQuantity = stockQuantity) {
}
