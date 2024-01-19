package jpabook.jpashop.service

import jpabook.jpashop.domain.Member
import jpabook.jpashop.repository.MemberRepository
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.beans.factory.annotation.Autowired
import java.lang.IllegalStateException

@Service
@Transactional
// @RequiredArgsConstructor
class MemberService(private val memberRepository: MemberRepository) {
//    @Autowired
//    private lateinit val memberRepository: MemberRepository

    // 회원 가입
    @Transactional
    fun join(member: Member): Long {
        validateDuplicateMember(member)
        memberRepository.save(member)
        return member.id
    }

    // 중복 회원 검증
    private fun validateDuplicateMember(member: Member) {
        val findMembers = memberRepository.findByName(member.name)
        if (findMembers.isNotEmpty()) {
            throw IllegalStateException("이미 존재하는 회원입니다.")
        }
    }

    // 회원 전체 조회
    @Transactional(readOnly = true) // 쓰기인 경우 최적화된다
    fun findMembers(): List<Member> {
        return memberRepository.findAll()
    }

    @Transactional(readOnly = true)
    fun findOne(id: Long): Member {
        return memberRepository.findOne(id)
    }

}