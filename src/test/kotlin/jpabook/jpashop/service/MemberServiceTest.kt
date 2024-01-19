package jpabook.jpashop.service

import jakarta.persistence.EntityManager
import jpabook.jpashop.domain.Member
import jpabook.jpashop.repository.MemberRepository
import jpabook.jpashop.service.MemberService
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.fail
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.Rollback
import org.springframework.transaction.annotation.Transactional
import java.lang.IllegalStateException


@SpringBootTest
@Transactional
class MemberServiceTest(
    @Autowired val em: EntityManager,
    @Autowired val memberService: MemberService,
    @Autowired val memberRepository: MemberRepository
) {

    @Test
//  @Rollback(false)
    fun `회원가입`() {
        // given
        val member = Member(name = "kim")

        // when
        val savedId = memberService.join(member)

        // then
        em.flush()
        assertEquals(member, memberRepository.findOne(savedId))
    }

    @Test()
    fun `중복 회원 예외`() {
        // given
        val member1 = Member(name = "kim")
        val member2 = Member(name = "kim")
        // when
        memberService.join(member1)
        assertThrows(IllegalStateException::class.java) { memberService.join(member2) }

    }

}