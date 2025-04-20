package com.example.mi_proyecto_backend.cuenta.config

import com.example.mi_proyecto_backend.cuenta.domain.events.UsuarioCreadoEvent
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.support.serializer.JsonDeserializer

@EnableKafka
@Configuration
class KafkaConsumerConfig {
    @Primary
    @Bean
    fun consumerFactory(): ConsumerFactory<String, UsuarioCreadoEvent> {
        val props = mapOf(
            ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG to "kafka:9092",
            ConsumerConfig.GROUP_ID_CONFIG to "cuenta-service-group",
            ConsumerConfig.AUTO_OFFSET_RESET_CONFIG to "earliest",
            ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG to StringDeserializer::class.java,
            ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG to JsonDeserializer::class.java
        )
        val deserializer = JsonDeserializer(UsuarioCreadoEvent::class.java, false).apply {
            addTrustedPackages("com.example.mi_proyecto_backend")
        }
        return DefaultKafkaConsumerFactory(props, StringDeserializer(), deserializer)
    }
    @Primary
    @Bean
    fun kafkaListenerContainerFactory() =
        ConcurrentKafkaListenerContainerFactory<String, UsuarioCreadoEvent>().also {
            it.consumerFactory = consumerFactory()
        }
}
