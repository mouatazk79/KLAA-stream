package klaa.mouataz.notification.config;

import lombok.Data;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class NotificationConfig {
    private String internalExchange="internal.exchange";
    private String notificationQueue="notification.queue";
    private String internalNotificationRoutingKey="internal.notification.routing-key";

    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(this.internalExchange);
    }

    @Bean
    public Queue queue(){
        return new Queue(this.notificationQueue);
    }

    @Bean
    public Binding binding(){
        return BindingBuilder.bind(queue())
                .to(exchange())
                .with(this.internalNotificationRoutingKey);
    }
}
