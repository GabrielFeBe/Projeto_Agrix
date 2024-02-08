package com.betrybe.agrix.dto;

import java.time.LocalDate;

/**
 * The type Crop creation dto.
 */
public record CropCreationDto(String name, Double plantedArea, LocalDate plantedDate,
                              LocalDate harvestDate) {

}
