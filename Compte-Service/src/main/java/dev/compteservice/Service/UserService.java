package dev.compteservice.Service;

import dev.compteservice.dto.UserRequestDto;
import dev.compteservice.dto.UserRespondDto;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserRespondDto SaveUser(UserRequestDto userRequestDto);
    List<UserRespondDto> getAllUser();
    UserRespondDto getUserById(Long id);
    UserRespondDto Update(UserRequestDto userRequestDto , Long id);
    Optional<UserRespondDto> DelleteUser(Long id);

}
