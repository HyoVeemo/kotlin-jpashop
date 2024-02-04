package jpabook.jpashop

import com.fasterxml.jackson.datatype.hibernate5.jakarta.Hibernate5JakartaModule
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class JpashopApplication {


}

fun main(args: Array<String>) {
    @Bean
    fun hibernate5Module(): Hibernate5JakartaModule {
        return Hibernate5JakartaModule()
    }
    runApplication<JpashopApplication>(*args)
}
