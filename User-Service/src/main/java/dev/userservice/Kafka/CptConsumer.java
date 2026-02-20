package dev.userservice.Kafka;

import dev.userservice.Service.CompteService;
import dev.userservice.dto.KafkaDto;
import dev.userservice.Mapper.CptMapper;
import dev.userservice.Model.CptModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CptConsumer {

    private final CompteService compteService;

    @KafkaListener(
            topics = "user-topic",
            groupId = "cpt-group",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void consume(KafkaDto dto) {
        log.info("Message reçu depuis Kafka: {}", dto);


        CptModel entity = CptMapper.toEntityFromKafka(dto);

        compteService.SaveCptEntity(entity);
    }
}
