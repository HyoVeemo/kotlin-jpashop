package jpabook.jpashop

import com.fasterxml.jackson.datatype.hibernate5.jakarta.Hibernate5JakartaModule
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class JpashopApplication {
    @Bean
    fun hibernate5Module(): Hibernate5JakartaModule {
        return Hibernate5JakartaModule()
    }
}

fun main(args: Array<String>) {
    runApplication<JpashopApplication>(*args)
}
