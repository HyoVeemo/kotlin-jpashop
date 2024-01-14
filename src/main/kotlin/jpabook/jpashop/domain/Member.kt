package jpabook.jpashop.domain

import jakarta.persistence.*

@Entity

class Member(
    @Id
    @GeneratedValue
    @Column
    val id: Long = 0,

    var name: String,

    val address: Address,

    @OneToMany(mappedBy = "member")
    var orders: List<Order> = emptyList()
) {


}