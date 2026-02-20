package dev.compteservice.dto;

public record UserRequestDto(
        String nom,
        String prenom,
        String age,
        String localisation
) {
}
