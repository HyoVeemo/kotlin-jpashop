package jpabook.jpashop.repository.order.simplequery

import jakarta.persistence.EntityManager
import org.springframework.stereotype.Repository

@Repository
class OrderSimpleQueryRepository(private val em: EntityManager) {
    fun findOrderDtos(): List<OrderSimpleQueryDto> {
        return em.createQuery(
            "select new jpabook.jpashop.repository.order.simplequery.OrderSimpleQueryDto(o.id, m.name, o.orderDate, o.status, d.address)" +
                    " from Order o" +
                    " join o.member m" +
                    " join o.delivery d", OrderSimpleQueryDto::class.java
        ).resultList
    }
}