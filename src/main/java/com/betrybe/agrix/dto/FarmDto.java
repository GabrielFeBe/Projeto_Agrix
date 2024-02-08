package com.betrybe.agrix.dto;

import com.betrybe.agrix.models.entities.Farm;

/**
 * The type Farm dto.
 */
public record FarmDto(Long id, String name, Double size) {

  /**
   * Dto to farm farm.
   *
   * @return the farm
   */
  public Farm dtoToFarm() {
    return new Farm(id, name, size, null);
  }

  /**
   * The type Response dto.
   *
   * @param <T> the type parameter
   */
  public record ResponseDto<T>(String message, T data) {

  }

}
