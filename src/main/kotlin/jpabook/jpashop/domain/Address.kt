package jpabook.jpashop.domain

import jakarta.persistence.Embeddable

@Embeddable
class Address(
    private val city: String,
    private val street: String,
    private val zipCode: String,
) {
}