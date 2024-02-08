package com.betrybe.agrix.service;

import com.betrybe.agrix.dto.CropCreationDto;
import com.betrybe.agrix.exceptions.FarmNotFound;
import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.models.entities.Farm;
import com.betrybe.agrix.models.repositories.CropRepository;
import com.betrybe.agrix.models.repositories.FarmRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type Farm service.
 */
@Service
public class FarmService {

  private FarmRepository farmRepository;


  private CropRepository cropRepository;

  /**
   * Instantiates a new Farm service.
   *
   * @param farmRepository the farm repository
   * @param cropRepository the crop repository
   */
  @Autowired
  public FarmService(FarmRepository farmRepository, CropRepository cropRepository) {
    this.farmRepository = farmRepository;
    this.cropRepository = cropRepository;
  }

  /**
   * Insert farm farm.
   *
   * @param farm the farm
   * @return the farm
   */
  @Transactional
  public Farm insertFarm(Farm farm) {
    return this.farmRepository.save(farm);
  }

  /**
   * Gets all farms.
   *
   * @return the all farms
   */
  @Transactional
  public List<Farm> getAllFarms() {
    return this.farmRepository.findAll();
  }

  /**
   * Gets farm by id.
   *
   * @param id the id
   * @return the farm by id
   */
  @Transactional
  public Farm getFarmById(Long id) {
    Optional<Farm> farm = this.farmRepository.findById(id);
    if (farm.isEmpty()) {
      throw new FarmNotFound();
    }
    return farm.get();
  }

  /**
   * Insert crop crop.
   *
   * @param farmId the farm id
   * @param crop   the crop
   * @return the crop
   */
  @Transactional
  public Crop insertCrop(Long farmId, CropCreationDto crop) {
    Optional<Farm> farm = this.farmRepository.findById(farmId);
    if (farm.isEmpty()) {
      throw new FarmNotFound();
    }
    Crop blankCrop = new Crop();
    blankCrop.setFarm(farm.get());
    blankCrop.setName(crop.name());
    blankCrop.setPlantedDate(crop.plantedDate());
    blankCrop.setHarvestDate(crop.harvestDate());
    blankCrop.setPlantedArea(crop.plantedArea());
    return this.cropRepository.save(blankCrop);
  }

  /**
   * Gets crop by farm id.
   *
   * @param farmId the farm id
   * @return the crop by farm id
   */
  @Transactional
  public List<Crop> getCropByFarmId(Long farmId) {
    Optional<Farm> farm = this.farmRepository.findById(farmId);
    if (farm.isEmpty()) {
      throw new FarmNotFound();
    }
    return farm.get().getCrops();
  }

}
