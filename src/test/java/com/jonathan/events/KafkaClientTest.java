package com.jonathan.events;

import com.jonathan.dto.ShopDTO;
import com.jonathan.event.KafkaClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith({SpringExtension.class})
public class KafkaClientTest {

    @InjectMocks
    private KafkaClient kafkaClient;

    @Mock
    private KafkaTemplate<String, ShopDTO> kafkaTemplate;

    private static final String SHOP_TOPIC_NAME = "SHOP_TOPIC";

    @Test
    public void testeSendMessage() {
        ShopDTO shopDTO = new ShopDTO();
        shopDTO.setStatus("SUCCESS");
        shopDTO.setBuyerIdentifier("b-1");

        kafkaClient.sendMessage(shopDTO);

        Mockito.verify(kafkaTemplate, Mockito.times(1))
                .send(SHOP_TOPIC_NAME, "b-1", shopDTO);
    }
}
