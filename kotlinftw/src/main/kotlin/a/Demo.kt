package a

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@SpringBootApplication
open class Demo {

    @Bean
    open fun dummyCLR(rr: ReservationRepository) = CommandLineRunner { args ->
        arrayOf("Justin", "Josh", "Jane").forEach { name ->
            rr.save(Reservation (name))
            println (name.needlessAddHiToAllString())
        }

        println (configuration("Bob", { this + "Bob" }))

        rr.findAll().forEach { println(it) }
    }
}

fun configuration(s: String, init: String.() -> String): String {
    return s.init()
}

fun String.needlessAddHiToAllString(): String {
    return this + " HI"
}

interface ReservationRepository : JpaRepository <Reservation, Long> {

    fun findByReservationName(reservationName: String): Collection<Reservation>
}


@RestController
open class GreetingsRestController {

    @RequestMapping ("/hi/{name}")
    open fun hi(locale: Locale, @PathVariable name: String): String {
        return "Hi ${name.capitalize()} from ${locale.country}"
    }
}

fun main(args: Array<String>) {
    SpringApplication.run(Demo::class.java, *args)
}
