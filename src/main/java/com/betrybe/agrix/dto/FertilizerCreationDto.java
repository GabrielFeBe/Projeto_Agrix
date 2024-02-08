package com.betrybe.agrix.dto;

import com.betrybe.agrix.models.entities.Fertilizer;

/**
 * The type Fertilizer creation dto.
 */
public record FertilizerCreationDto(String name, String composition, String brand) {

  /**
   * Dto to fertilizer fertilizer.
   *
   * @return the fertilizer
   */
  public Fertilizer dtoToFertilizer() {
    Fertilizer fertilizer = new Fertilizer();
    fertilizer.setName(name);
    fertilizer.setComposition(composition);
    fertilizer.setBrand(brand);
    return fertilizer;
  }

}
