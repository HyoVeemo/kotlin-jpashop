package jpabook.jpashop.controller

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull


class BookForm(
    var id: Long = 0,
    @field:NotEmpty(message = "상품 이름은 필수입니다.")
    var name: String = "",
    @field:NotNull(message = "가격은 필수입니다.")
    var price: Int = 0,
    var stockQuantity: Int = 0,
    var author: String = "",
    var isbn: String = ""
) {
}