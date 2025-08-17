package com.pps.demo.core.config

import com.pps.demo.core.kafka.models.DemoEvent
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
  fun demoEventConsumerFactory(): ConsumerFactory<String, DemoEvent> {
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
    return DefaultKafkaConsumerFactory<String, DemoEvent>(configProps).also {
      it.valueDeserializer = JsonDeserializer(DemoEvent::class.java) // use specific deserializer for event type
    }
  }

  @Bean
  fun demoEventKafkaListenerContainerFactory(
    demoEventConsumerFactory: ConsumerFactory<String, DemoEvent>,
  ): ConcurrentKafkaListenerContainerFactory<String, DemoEvent> {
    val factory = ConcurrentKafkaListenerContainerFactory<String, DemoEvent>()
    factory.consumerFactory = demoEventConsumerFactory
    factory.containerProperties.ackMode = ContainerProperties.AckMode.MANUAL_IMMEDIATE
    factory.setConcurrency(3) // Max number of threads consuming messages
    return factory
  }
}
