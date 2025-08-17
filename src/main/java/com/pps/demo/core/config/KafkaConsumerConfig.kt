package com.pps.demo.core.config

import com.pps.demo.core.kafka.models.OrderEvent
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.listener.ContainerProperties
import org.springframework.kafka.support.serializer.JsonDeserializer

@Configuration
class KafkaConsumerConfig(
  @Value("\${spring.kafka.bootstrap-servers}")
  private val bootstrapServers: String,
) {

  @Bean
  fun demoEventConsumerFactory(): ConsumerFactory<String, OrderEvent> {
    val configProps = mapOf(
      ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG to bootstrapServers,
      ConsumerConfig.CLIENT_ID_CONFIG to "demo-consumer",
      ConsumerConfig.GROUP_ID_CONFIG to "demo-group",
      ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG to StringDeserializer::class.java,
      ConsumerConfig.AUTO_OFFSET_RESET_CONFIG to "earliest",
      ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG to false,
      JsonDeserializer.TRUSTED_PACKAGES to "*",
      JsonDeserializer.USE_TYPE_INFO_HEADERS to false,
    )
    return DefaultKafkaConsumerFactory<String, OrderEvent>(configProps).also {
      it.valueDeserializer = JsonDeserializer(OrderEvent::class.java)
    }
  }

  @Bean
  fun demoEventKafkaListenerContainerFactory(
    orderEventConsumerFactory: ConsumerFactory<String, OrderEvent>,
  ): ConcurrentKafkaListenerContainerFactory<String, OrderEvent> {
    val factory = ConcurrentKafkaListenerContainerFactory<String, OrderEvent>()
    factory.consumerFactory = orderEventConsumerFactory
    factory.containerProperties.ackMode = ContainerProperties.AckMode.MANUAL_IMMEDIATE
    factory.setConcurrency(3) // Max number of threads consuming messages
    return factory
  }
}
