package ru.rgrabelnikov.ordersapi.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.function.cloudevent.CloudEventMessageBuilder;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageDeliveryException;
import org.springframework.stereotype.Service;
import ru.rgrabelnikov.ordersapi.service.EventSenderService;

import java.util.UUID;

import static org.springframework.cloud.function.cloudevent.CloudEventMessageUtils.AMQP_ATTR_PREFIX;

@Service
@RequiredArgsConstructor
public class DefaultEventSenderService implements EventSenderService {

    private static final String SOURCE = "orders-api";
    private static final String TYPE = "OrderCreated";

    private static final String BINDING = "orderCreatedProducer-out-0";

    private final StreamBridge streamBridge;

    @Override
    public <T> void send(final T payload) {
        final Message<T> message = CloudEventMessageBuilder.withData(payload)
                .setId(UUID.randomUUID().toString())
                .setSource(SOURCE)
                .setType(TYPE)
                .build(AMQP_ATTR_PREFIX);

        final boolean sendResult = streamBridge.send(BINDING, message);
        if (!sendResult) {
            throw new MessageDeliveryException(message, "Message was not sent to " + BINDING);
        }
    }
}
