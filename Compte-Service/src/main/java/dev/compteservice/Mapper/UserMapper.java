package dev.compteservice.Mapper;

import dev.compteservice.Model.UserModel;
import dev.compteservice.dto.UserRequestDto;
import dev.compteservice.dto.UserRespondDto;

public class UserMapper {

    public static UserModel toEntity(UserRequestDto dto) {
        if (dto == null) {
            return null;
        }

        return UserModel.builder()
                .nom(dto.nom())
                .prenom(dto.prenom())
                .age(dto.age())
                .localisation(dto.localisation())
                .build();
    }

    public static UserRespondDto toResponseDto(UserModel user) {
        if (user == null) {
            return null;
        }

        return new UserRespondDto(
                user.getId(),
                user.getNom(),
                user.getPrenom(),
                user.getAge(),
                user.getLocalisation()
        );
    }

    public static UserRequestDto toDto(UserModel user) {
        if (user == null) {
            return null;
        }

        return new UserRequestDto(
                user.getNom(),
                user.getPrenom(),
                user.getAge(),
                user.getLocalisation()
        );
    }

    public static void updateEntity(UserModel existingUser, UserRequestDto dto) {
        if (existingUser == null || dto == null) {
            return;
        }

        existingUser.setNom(dto.nom());
        existingUser.setPrenom(dto.prenom());
        existingUser.setAge(dto.age());
        existingUser.setLocalisation(dto.localisation());
    }
}
