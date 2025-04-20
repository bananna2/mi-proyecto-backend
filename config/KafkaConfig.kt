package com.example.mi_proyecto_backend.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializerWrapper
import org.springframework.kafka.support.serializer.JsonDeserializer
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.kafka.annotation.EnableKafka

@Configuration
@EnableKafka
class KafkaConfig {

    @Bean
    fun jsonDeserializer(): JsonDeserializer<Any> {
        val deserializer = JsonDeserializer(Any::class.java)
        deserializer.addTrustedPackages("com.example.mi_proyecto_backend.usuario.domain.events")
        return deserializer
    }

    @Bean
    fun consumerFactory(): ConsumerFactory<String, Any> {
        return DefaultKafkaConsumerFactory(
            mapOf<String, Any>(
                "key.deserializer" to StringDeserializer::class.java,
                "value.deserializer" to ErrorHandlingDeserializerWrapper(JsonDeserializer(Any::class.java))
            )
        )
    }
}
