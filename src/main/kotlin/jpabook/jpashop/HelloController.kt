package jpabook.jpashop

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class HelloController {

    @GetMapping("hello")
    fun hello(model: Model): String{
        model.addAttribute("data", "hello!!!")

        // 화면 이름: hello.html
        return "hello"
    }
}