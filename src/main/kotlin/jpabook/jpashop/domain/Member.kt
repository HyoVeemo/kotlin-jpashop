package jpabook.jpashop.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*

@Entity
class Member(
    @Id
    @GeneratedValue
    @Column
    val id: Long = 0,

    var name: String,

    var address: Address? = null,
) {
    @JsonIgnore
    @OneToMany(mappedBy = "member")
    var orders: MutableList<Order> = mutableListOf()
}