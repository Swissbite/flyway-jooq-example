package net.daester.david.flywayjooqexample.infrastructure.jooq

import org.jooq.conf.Settings
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class JooqConfig {

    @Bean
    fun jooqProperties(): Settings {
        return Settings().withRenderSchema(false)
    }

}