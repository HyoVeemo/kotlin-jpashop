package jpabook.jpashop

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id

@Entity
class Member (
    @Id
    @GeneratedValue
    val id: Long = 0,

    val userName: String
){

}