package jpabook.jpashop

import jakarta.transaction.Transactional
import jpabook.jpashop.domain.Address
import jpabook.jpashop.domain.Member
import jpabook.jpashop.repository.MemberRepositoryOld
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest


@SpringBootTest
class MemberRepositoryTest(@Autowired val memberRepositoryOld: MemberRepositoryOld) {

    @Test
    @Transactional
    fun testMember() {
        // given
        val member = Member(name = "memberA", address = Address("서울", "소월로", "00000"))

        // when
        val saveMember = memberRepositoryOld.save(member)
        val findMember = memberRepositoryOld.findOne(member.id)

        // then
        Assertions.assertThat(findMember.id).isEqualTo(member.id)
        Assertions.assertThat(findMember.name).isEqualTo(member.name)
        Assertions.assertThat(findMember).isEqualTo(member)
    }
}