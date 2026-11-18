package hr.krvnaveza.domain.dto;

import hr.krvnaveza.domain.enums.StatusRezervacije;

import java.time.OffsetDateTime;
import java.util.UUID;

public record RezervacijaResponse(
        Long id,
        UUID qrToken,
        OffsetDateTime pocetak,
        OffsetDateTime kraj,
        StatusRezervacije status,
        Long darivateljId,
        LokacijaResponse lokacija) {}