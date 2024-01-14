package jpabook.jpashop.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id

@Entity
class Delivery (
    @Id
    @GeneratedValue
    @Column(name="delivery_id")
    val id: Long,
){
}