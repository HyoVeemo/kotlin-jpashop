package jpabook.jpashop.repository

import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.persistence.EntityManager
import jpabook.jpashop.domain.Order
import jpabook.jpashop.domain.OrderStatus
import jpabook.jpashop.domain.QMember.member
import jpabook.jpashop.domain.QOrder.order
import org.springframework.stereotype.Repository

@Repository
class OrderRepository(
    private val em: EntityManager,
) {
    private val query: JPAQueryFactory = JPAQueryFactory(em)

    fun save(order: Order) {
        em.persist(order)
    }

    fun findOne(id: Long): Order {
        return em.find(Order::class.java, id)
    }

    fun findAll2(orderSearch: OrderSearch): List<Order> {
        return query.select(order)
            .from(order)
            .join(order.member, member)
            .where(statusEq(orderSearch.orderStatus))
            .limit(1000)
            .fetch()
    }


    fun findAll(orderSearch: OrderSearch): List<Order> {
        return query.select(order)
            .from(order)
            .join(order.member, member)
            .where(statusEq(orderSearch.orderStatus), nameLike(orderSearch.memberName))
            .limit(1000)
            .fetch()
    }

    private fun nameLike(name: String?): BooleanExpression? =
        if (name == null) null else member.name.like(name)

    private fun statusEq(statusCond: OrderStatus?) =
        if (statusCond == null) null else order.status.eq(statusCond)


    fun findAllWithMemberDelivery(): List<Order> {
        return em.createQuery(
            "select o from Order o" +
                    " join fetch o.member m" +
                    " join fetch o.delivery d", Order::class.java
        ).resultList
    }

    fun findAllWithMemberDelivery(offset: Int, limit: Int): List<Order> {
        return em.createQuery(
            "select o from Order o" +
                    " join fetch o.member m" +
                    " join fetch o.delivery d", Order::class.java
        ).setFirstResult(offset).setMaxResults(limit).resultList
    }

    fun findWithItem(): List<Order> {
        return em.createQuery(
            "select distinct o from Order o" +
                    " join fetch o.member m" +
                    " join fetch o.delivery d", Order::class.java
        ).setFirstResult(1)
            .setMaxResults(100)
            .resultList
    }
}