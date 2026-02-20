package dev.compteservice.dto;

public record UserRespondDto(
        Long id,
        String nom,
        String prenom,
        String age,
        String localisation
) {
}
