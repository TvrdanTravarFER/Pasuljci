package hr.krvnaveza.domain.dto;

import hr.krvnaveza.domain.enums.StatusDoze;

public record DozaResponse(Long id, StatusDoze status) {}