package jpabook.jpashop.domain

import jakarta.persistence.*

@Entity
class Category(
    @Id
    @GeneratedValue
    @Column(name = "category_id")
    val id: Long = 0,

    val name: String,

    @ManyToMany
    @JoinTable(
        name = "category_item",
        joinColumns = [JoinColumn(name = "category_id")],
        inverseJoinColumns = [JoinColumn(name = "item_id")]
    )
    var items: MutableList<Item> = mutableListOf(),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    var parent: Category? = null,

    @OneToMany(mappedBy = "parent")
    val child: MutableList<Category> = mutableListOf()
) {
    fun addChildCategory(child: Category) {
        this.child += child
        child.parent = this
    }
}