package dev.userservice.dto;

public record CptRespondDto(
        Long id,
        String nom,
        String prenom,
        String age,
        String pays
) {
}
