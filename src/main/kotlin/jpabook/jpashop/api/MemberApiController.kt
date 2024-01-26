package jpabook.jpashop.api

import jakarta.validation.Valid
import jakarta.validation.constraints.NotEmpty
import jpabook.jpashop.domain.Member
import jpabook.jpashop.service.MemberService
import lombok.Data
import org.springframework.web.bind.annotation.*

@RestController
class MemberApiController(
    private val memberService: MemberService
) {

    @PostMapping("/api/v1/members")
    fun saveMemberV1(@RequestBody @Valid member: Member): CreateMemberResponse {
        val id = memberService.join(member)
        return CreateMemberResponse(id)
    }

    @PostMapping("/api/v2/members")
    fun saveMemberV2(@RequestBody @Valid request: CreateMemberRequest): CreateMemberResponse {
        val member = Member(name = request.name)
        val id = memberService.join(member)
        return CreateMemberResponse(id)
    }

    @PutMapping("/api/v2/members/{id}")
    fun updateMemberV2(
        @PathVariable("id") id: Long,
        @RequestBody @Valid request: UpdateMemberRequest
    ): UpdateMemberResponse {
        memberService.update(id, request.name)
        val member = memberService.findOne(id)
        return UpdateMemberResponse(member.id, member.name)
    }

    @Data
    data class CreateMemberResponse(
        val id: Long
    )

    @Data
    data class CreateMemberRequest(
        @field:NotEmpty
        val name: String
    )

    @Data
    data class UpdateMemberResponse(
        val id: Long,
        val name: String
    )

    @Data
    data class UpdateMemberRequest(
        val name: String
    )
}


