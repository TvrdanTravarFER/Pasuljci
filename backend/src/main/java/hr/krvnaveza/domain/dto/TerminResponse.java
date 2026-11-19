package hr.krvnaveza.domain.dto;

import hr.krvnaveza.domain.enums.StatusRezervacije;

import java.time.OffsetDateTime;

public record TerminResponse(OffsetDateTime pocetak, OffsetDateTime kraj, StatusRezervacije status) {
}
