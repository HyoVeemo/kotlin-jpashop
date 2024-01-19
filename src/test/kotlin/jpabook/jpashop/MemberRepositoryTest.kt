package jpabook.jpashop

import jakarta.transaction.Transactional
import jpabook.jpashop.domain.Member
import jpabook.jpashop.repository.MemberRepository
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest


@SpringBootTest
class MemberRepositoryTest(@Autowired val memberRepository: MemberRepository) {

    @Test
    @Transactional
    fun testMember() {
        // given
        val member = Member(name = "memberA")

        // when
        val saveMember = memberRepository.save(member)
        val findMember = memberRepository.findOne(member.id)

        // then
        Assertions.assertThat(findMember.id).isEqualTo(member.id)
        Assertions.assertThat(findMember.name).isEqualTo(member.name)
        Assertions.assertThat(findMember).isEqualTo(member)
    }
}