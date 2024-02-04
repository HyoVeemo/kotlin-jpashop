package jpabook.jpashop.repository

import jakarta.persistence.EntityManager
import jakarta.persistence.criteria.*
import jpabook.jpashop.domain.Order
import org.springframework.stereotype.Repository

@Repository
class OrderRepository(private val em: EntityManager) {
    fun save(order: Order) {
        em.persist(order)
    }

    fun findOne(id: Long): Order {
        return em.find(Order::class.java, id)
    }

    fun findAll(orderSearch: OrderSearch): List<Order> {
        val cb: CriteriaBuilder = em.criteriaBuilder
        val cq: CriteriaQuery<Order> = cb.createQuery(Order::class.java)
        val o: Root<Order> = cq.from(Order::class.java)
        val m: Join<Object, Object> = o.join("member", JoinType.INNER)

        val criteria = ArrayList<Predicate>()
        if (orderSearch.orderStatus != null) {
            val status = cb.equal(o.get<Order>("status"), orderSearch.orderStatus)
            criteria.add(status)
        }
        if (!orderSearch.memberName.isNullOrBlank()) {
            val name = cb.like(m.get("name"), "%" + orderSearch.memberName + "%")
            criteria.add(name)
        }


        cq.where(cb.and(*criteria.toTypedArray()))
        return em.createQuery(cq).setMaxResults(1000).resultList
    }

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
                    " join fetch o.delivery d" +
                    " join fetch o.orderItems oi" +
                    " join fetch oi.item i", Order::class.java
        ).setFirstResult(1)
            .setMaxResults(100)
            .resultList
    }
}