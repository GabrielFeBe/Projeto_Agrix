package com.betrybe.agrix.dto;

import com.betrybe.agrix.models.entities.Crop;
import java.time.LocalDate;

/**
 * The type Crop dto.
 */
public record CropDto(Long id, Long farmId, String name, Double plantedArea, LocalDate plantedDate,
                      LocalDate harvestDate) {


}
