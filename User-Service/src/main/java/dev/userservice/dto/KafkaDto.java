package dev.userservice.dto;

public record KafkaDto(
        String nom,
        String prenom,
        String age,
        String localisation
) {
}
