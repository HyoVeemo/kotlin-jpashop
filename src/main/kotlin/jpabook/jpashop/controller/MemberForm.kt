package jpabook.jpashop.controller

import jakarta.validation.constraints.NotEmpty


class MemberForm(
) {
    @field:NotEmpty(message = "회원 이름은 필수 입니다.")
    var name: String = ""
    var city: String = ""
    var street: String = ""
    var zipcode: String = ""
}