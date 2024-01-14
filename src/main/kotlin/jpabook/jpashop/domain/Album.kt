package jpabook.jpashop.domain

class Album(
    id: Long,
    name: String,
    price: Int,
    stockQuantity: Int,
    val artist: String,
    val etc: String
) : Item(id, name, price, stockQuantity) {
}