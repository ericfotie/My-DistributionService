package dev.userservice.Service.ServiceImpl;

import dev.userservice.Mapper.CptMapper;
import dev.userservice.Model.CptModel;
import dev.userservice.Repository.CptRepository;
import dev.userservice.Service.CompteService;
import dev.userservice.dto.CptRequestDto;
import dev.userservice.dto.CptRespondDto;
import dev.userservice.dto.KafkaDto;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CptServiceImpl implements CompteService {

    private final CptRepository cptRepository;

    public CptServiceImpl(CptRepository cptRepository) {
        this.cptRepository = cptRepository;
    }


    @KafkaListener(topics = "user-topic", groupId = "cpt-group", containerFactory = "kafkaListenerContainerFactory")
    @Transactional
    public void consumeUser(KafkaDto kafkaDto) {
        CptModel entity = CptMapper.toEntityFromKafka(kafkaDto);
        cptRepository.save(entity);
        System.out.println("Message reçu et sauvegardé : " + kafkaDto);
    }

    @Override
    public CptRespondDto SaveCpt(CptRequestDto cptRequestDto) {
        CptModel entity = CptMapper.toEntity(cptRequestDto);
        CptModel saved = cptRepository.save(entity);
        return CptMapper.toResponseDto(saved);
    }

    @Override
    public List<CptRespondDto> getAllCpt() {
        return cptRepository.findAll()
                .stream()
                .map(CptMapper::toResponseDto)
                .collect(Collectors.toList());
    }


    @Override
    public Optional<CptRespondDto> getByIdCpt(Long id) {
        return cptRepository.findById(id)
                .map(CptMapper::toResponseDto);
    }


    @Override
    public Optional<CptRespondDto> delleteCpt(Long id) {
        return cptRepository.findById(id)
                .map(cpt -> {
                    cptRepository.delete(cpt);
                    return CptMapper.toResponseDto(cpt);
                });
    }

    @Override
    public CptRespondDto UpdateCpt(CptRespondDto cptRespondDto, Long id) {
        CptModel existing = cptRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cpt not found with id: " + id));

        existing.setNom(cptRespondDto.nom());
        existing.setPrenom(cptRespondDto.prenom());
        existing.setAge(cptRespondDto.age());
        existing.setPays(cptRespondDto.pays());

        CptModel updated = cptRepository.save(existing);
        return CptMapper.toResponseDto(updated);
    }

    @Override
    public void SaveCptEntity(CptModel entity) {

    }
}
