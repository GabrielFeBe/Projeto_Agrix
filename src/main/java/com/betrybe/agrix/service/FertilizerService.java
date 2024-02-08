package com.betrybe.agrix.service;

import com.betrybe.agrix.dto.FertilizerCreationDto;
import com.betrybe.agrix.exceptions.FertilizerNotFound;
import com.betrybe.agrix.models.entities.Fertilizer;
import com.betrybe.agrix.models.repositories.FertilizerRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type Fertilizer service.
 */
@Service
public class FertilizerService {

  @Autowired
  private FertilizerRepository fertilizerRepository;

  /**
   * Create fertilizer fertilizer.
   *
   * @param fertilizerCreationDto the fertilizer creation dto
   * @return the fertilizer
   */
  public Fertilizer createFertilizer(FertilizerCreationDto fertilizerCreationDto) {
    return this.fertilizerRepository.save(fertilizerCreationDto.dtoToFertilizer());
  }

  /**
   * Gets all fertilizers.
   *
   * @return the all fertilizers
   */
  public List<Fertilizer> getAllFertilizers() {
    return this.fertilizerRepository.findAll();
  }

  /**
   * Gets fertilizer by id.
   *
   * @param id the id
   * @return the fertilizer by id
   */
  public Fertilizer getFertilizerById(Long id) {
    var opt = this.fertilizerRepository.findById(id);
    if (opt.isEmpty()) {
      throw new FertilizerNotFound();
    }
    return opt.get();
  }

}
