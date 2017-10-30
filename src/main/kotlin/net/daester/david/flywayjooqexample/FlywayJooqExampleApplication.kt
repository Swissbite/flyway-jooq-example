package net.daester.david.flywayjooqexample

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication(scanBasePackageClasses = arrayOf(FlywayJooqExampleApplication::class))
class FlywayJooqExampleApplication {
    companion object {
        @JvmStatic fun main(args: Array<String>) {
            SpringApplication.run(FlywayJooqExampleApplication::class.java, *args)
        }
    }
}
