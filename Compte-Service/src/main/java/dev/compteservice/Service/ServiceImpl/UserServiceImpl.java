package dev.compteservice.Service.ServiceImpl;

import dev.compteservice.Mapper.UserMapper;
import dev.compteservice.Model.UserModel;
import dev.compteservice.Repository.UserRepository;
import dev.compteservice.Service.UserService;
import dev.compteservice.dto.UserRequestDto;
import dev.compteservice.dto.UserRespondDto;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final KafkaTemplate<String, UserRespondDto> kafkaTemplate;
    private final String topic = "user-topic";

    public UserServiceImpl(UserRepository userRepository,
                           KafkaTemplate<String, UserRespondDto> kafkaTemplate) {
        this.userRepository = userRepository;
        this.kafkaTemplate = kafkaTemplate;
    }


    @Override
    public UserRespondDto SaveUser(UserRequestDto userRequestDto) {

        UserModel savedUser = userRepository.save(
                UserMapper.toEntity(userRequestDto)
        );

        UserRespondDto response = UserMapper.toResponseDto(savedUser);


        kafkaTemplate.send(topic, response);

        return response;
    }

    @Override
    public List<UserRespondDto> getAllUser() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::toResponseDto)
                .toList();
    }


    @Override
    public UserRespondDto getUserById(Long id) {
        return userRepository.findById(id)
                .map(UserMapper::toResponseDto)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }


    @Override
    public UserRespondDto Update(UserRequestDto userRequestDto, Long id) {

        UserModel user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        UserMapper.updateEntity(user, userRequestDto);

        UserModel updatedUser = userRepository.save(user);
        UserRespondDto response = UserMapper.toResponseDto(updatedUser);

        kafkaTemplate.send(topic, response);

        return response;
    }


    @Override
    public Optional<UserRespondDto> DelleteUser(Long id) {
        return userRepository.findById(id)
                .map(user -> {
                    userRepository.delete(user);
                    return UserMapper.toResponseDto(user);
                });
    }
}
