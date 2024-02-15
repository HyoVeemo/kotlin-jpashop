package jpabook.jpashop.repository

import jpabook.jpashop.domain.Order
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
@Transactional(readOnly = true)
interface OrderRepositoryImpl : JpaRepository<Order, Long> {
}