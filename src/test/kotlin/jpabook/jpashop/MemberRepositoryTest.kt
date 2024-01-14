package jpabook.jpashop

import jakarta.transaction.Transactional
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest


@SpringBootTest
class MemberRepositoryTest(@Autowired val memberRepository: MemberRepository){

    @Test
    @Transactional
    fun testMember() {
        // given
        val member = Member(userName = "memberA")

        // when
        val savedId = memberRepository.save(member)
        val findMember = memberRepository.find(savedId)

        // then
        Assertions.assertThat(findMember.id).isEqualTo(member.id)
        Assertions.assertThat(findMember.userName).isEqualTo(member.userName)
        Assertions.assertThat(findMember).isEqualTo(member)
    }
}