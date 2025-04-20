package com.example.mi_proyecto_backend.config

import org.apache.kafka.clients.admin.NewTopic
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.TopicBuilder

@Configuration
class KafkaConfig {

    @Bean
    fun usuarioTopic(): NewTopic {
        return TopicBuilder.name("usuario-events")
            .partitions(3)
            .replicas(1)
            .build()
    }

    @Bean
    fun cuentaTopic(): NewTopic {
        return TopicBuilder.name("cuenta-events")
            .partitions(3)
            .replicas(1)
            .build()
    }
}