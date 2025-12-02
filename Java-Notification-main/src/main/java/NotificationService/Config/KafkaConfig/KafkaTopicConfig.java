package NotificationService.Config.KafkaConfig;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
@Primary
public class KafkaTopicConfig {
    @Bean
    public NewTopic kafkatopic(){
        return TopicBuilder.name("Notifications")
                .build();
    }
}
