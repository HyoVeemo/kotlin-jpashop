package jpabook.jpashop.domain

import jakarta.persistence.Entity

@Entity
class Movie(
    id: Long,
    name: String,
    price: Int,
    stockQuantity: Int,
    val director: String,
    val actor: String
) : Item(id, name, price, stockQuantity) {

}