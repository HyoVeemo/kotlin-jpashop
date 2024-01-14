package jpabook.jpashop.domain

import jakarta.persistence.*

@Entity
@Inheritance (strategy = InheritanceType.SINGLE_TABLE)
abstract class Item (
    @Id
    @GeneratedValue
    @Column(name="item_id")
    val id: Long,

    val name: String,

    val price: Int,

    val stockQuantity: Int,

    ){
    @ManyToMany
    val categories: List<Category> = emptyList()
}