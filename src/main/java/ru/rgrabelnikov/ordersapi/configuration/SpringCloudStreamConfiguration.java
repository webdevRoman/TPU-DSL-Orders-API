package ru.rgrabelnikov.ordersapi.configuration;

import com.rabbitmq.client.DefaultSaslConfig;
import org.springframework.boot.amqp.autoconfigure.ConnectionFactoryCustomizer;
import org.springframework.boot.amqp.autoconfigure.RabbitProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringCloudStreamConfiguration {

    @Bean
    public ConnectionFactoryCustomizer connectionFactoryCustomizer(final RabbitProperties rabbitProperties) {
        if (Boolean.TRUE.equals(rabbitProperties.getSsl().getEnabled()) && rabbitProperties.getSsl().getKeyStore() != null) {
            return connectionFactory -> connectionFactory.setSaslConfig(DefaultSaslConfig.EXTERNAL);
        }
        return connectionFactory -> connectionFactory.setSaslConfig(DefaultSaslConfig.PLAIN);
    }
}
