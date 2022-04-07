package com.jonathan.events;

import com.jonathan.dto.ShopDTO;
import com.jonathan.dto.ShopItemDTO;
import com.jonathan.event.ReceiverKafkaMessage;
import com.jonathan.model.Shop;
import com.jonathan.repository.ShopRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class ReceiveKafkaMessageTests {

    @InjectMocks
    private ReceiverKafkaMessage receiverKafkaMessage;

    @Mock
    private ShopRepository shopRepository;

    @Test
    public void testSuccessfulMessageReceived() {
        ShopDTO shopDTO = new ShopDTO();
        shopDTO.setStatus("SUCCESS");
        ShopItemDTO shopItemDTO = new ShopItemDTO();
        shopItemDTO.setAmount(1000);
        shopItemDTO.setProductIdentifier("product-1");
        shopItemDTO.setPrice((float) 100);
        shopDTO.getItems().add(shopItemDTO);
        Shop shop = Shop.convert(shopDTO);
        Mockito.when(shopRepository.findByIdentifier(shopDTO.getIdentifier())).thenReturn(shop);
        receiverKafkaMessage.listenShopEvents(shopDTO);
        Mockito.verify(shopRepository, Mockito.times(1))
                .findByIdentifier(shopDTO.getIdentifier());
        Mockito.verify(shopRepository, Mockito.times(1)).save(shop);
    }
}

