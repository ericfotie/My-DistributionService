package dev.userservice.Mapper;

import dev.userservice.Model.CptModel;
import dev.userservice.dto.CptRequestDto;
import dev.userservice.dto.CptRespondDto;
import dev.userservice.dto.KafkaDto;

public class CptMapper {
    public static CptModel toEntity(CptRequestDto dto) {
        if (dto == null) {
            return null;
        }

        return CptModel.builder()
                .nom(dto.nom())
                .prenom(dto.prenom())
                .age(dto.age())
                .pays(dto.pays())
                .build();
    }
    public static CptRespondDto toResponseDto(CptModel cpt) {
        if (cpt == null) {
            return null;
        }

        return new CptRespondDto(
                cpt.getId(),
                cpt.getNom(),
                cpt.getPrenom(),
                cpt.getAge(),
                cpt.getPays()
        );
    }

    public static void updateEntity(CptModel existingCpt, CptRequestDto dto) {
        if (existingCpt == null || dto == null) {
            return;
        }

        existingCpt.setNom(dto.nom());
        existingCpt.setPrenom(dto.prenom());
        existingCpt.setAge(dto.age());
        existingCpt.setPays(dto.pays());
    }
    public static CptModel toEntityFromKafka(KafkaDto dto) {
        if (dto == null) return null;

        return CptModel.builder()
                .nom(dto.nom())
                .prenom(dto.prenom())
                .age(dto.age())
                .pays(dto.localisation())
                .build();
    }

}
