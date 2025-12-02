package NotificationService.Config.KafkaConfig;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProdConfig {
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapservers;

    public Map<String, Object> prodconfig()
    {
        HashMap<String, Object> props=new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,bootstrapservers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class);
        return props;
    }
    @Bean
    public ProducerFactory<String,String> prodFactory()
    {
        return new DefaultKafkaProducerFactory<>(prodconfig());
    }
    @Bean
    public KafkaTemplate<String,String> kafkaTemplate(ProducerFactory<String,String> producerFactory)
    {
        return new KafkaTemplate<>(producerFactory);
    }
}
