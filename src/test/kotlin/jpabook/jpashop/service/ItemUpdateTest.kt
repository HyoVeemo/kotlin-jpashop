package jpabook.jpashop.service

import jakarta.persistence.EntityManager
import jpabook.jpashop.domain.Book
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ItemUpdateTest(@Autowired val em: EntityManager) {

    @Test
    fun updateTest() {
        val book = em.find(Book::class.java, 1L)


        // TX
        book.name = "변경됨"

        // 변경 감지 == dirty checking
        // TX

    }
}