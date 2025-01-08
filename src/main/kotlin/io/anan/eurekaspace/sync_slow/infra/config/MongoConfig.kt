package io.anan.eurekaspace.sync_slow.infra.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mapping.model.SnakeCaseFieldNamingStrategy
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration

@Configuration
class MongoConfig : AbstractMongoClientConfiguration() {

    override fun getDatabaseName(): String {
        return "asyncboostdb"
    }

    @Bean
    override fun fieldNamingStrategy() = SnakeCaseFieldNamingStrategy()
}