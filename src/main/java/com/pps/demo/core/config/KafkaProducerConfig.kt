package com.pps.demo.core.config

import com.pps.demo.core.kafka.models.DemoEvent
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory
import org.springframework.kafka.support.serializer.JsonSerializer

@Configuration
class KafkaProducerConfig(
  @Value("\${spring.kafka.bootstrap-servers}")
  private val bootstrapServers: String,
) {
  @Bean
  fun demoEventProducerFactory(): ProducerFactory<String, DemoEvent> {
    val configProps = mapOf(
      ProducerConfig.BOOTSTRAP_SERVERS_CONFIG to bootstrapServers,
      ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG to StringSerializer::class.java,
      ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG to JsonSerializer::class.java,
      ProducerConfig.ACKS_CONFIG to "all",
      ProducerConfig.RETRIES_CONFIG to 3,
      ProducerConfig.LINGER_MS_CONFIG to 1,
    )
    return DefaultKafkaProducerFactory(configProps)
  }

  @Bean
  fun demoEventKafkaTemplate(
    demoEventProducerFactory: ProducerFactory<String, DemoEvent>,
  ): KafkaTemplate<String, DemoEvent> {
    return KafkaTemplate(demoEventProducerFactory)
  }
}
