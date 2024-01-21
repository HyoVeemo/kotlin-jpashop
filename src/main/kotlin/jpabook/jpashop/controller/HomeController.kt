package jpabook.jpashop.controller

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller
//@Slf4j - 코틀린과 호환 x
class HomeController {
    private val log = LoggerFactory.getLogger(this::class.java)

    @RequestMapping("/")
    fun home(): String {
        log.info("home controller")
        return "home"
    }

}