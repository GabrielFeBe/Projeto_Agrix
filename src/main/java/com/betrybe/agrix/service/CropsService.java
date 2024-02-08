package com.betrybe.agrix.service;

import com.betrybe.agrix.dto.CropDto;
import com.betrybe.agrix.exceptions.CropNotFound;
import com.betrybe.agrix.exceptions.FertilizerNotFound;
import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.models.entities.Fertilizer;
import com.betrybe.agrix.models.repositories.CropRepository;
import com.betrybe.agrix.models.repositories.FertilizerRepository;
import jakarta.transaction.Transactional;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type Crops service.
 */
@Service
public class CropsService {

  private CropRepository cropRepository;

  private FertilizerRepository fertilizerRepository;

  /**
   * Instantiates a new Crops service.
   *
   * @param cropRepository       the crop repository
   * @param fertilizerRepository the fertilizer repository
   */
  @Autowired
  public CropsService(CropRepository cropRepository, FertilizerRepository fertilizerRepository) {
    this.cropRepository = cropRepository;
    this.fertilizerRepository = fertilizerRepository;
  }

  /**
   * Gets all crops.
   *
   * @return the all crops
   */
  @Transactional
  public List<Crop> getAllCrops() {
    return this.cropRepository.findAll();
  }

  /**
   * Gets crop by id.
   *
   * @param id the id
   * @return the crop by id
   */
  @Transactional
  public Crop getCropById(Long id) {
    return this.lookIfCropExist(id);
  }

  /**
   * Gets by date.
   *
   * @param start the start
   * @param end   the end
   * @return the by date
   */
  @Transactional
  public List<Crop> getByDate(LocalDate start, LocalDate end) {
    return this.cropRepository.findByDateBetween(start, end);
  }

  /**
   * Relate fertilizer to crop.
   *
   * @param cropId       the crop id
   * @param fertilizerId the fertilizer id
   */
  @Transactional
  public void relateFertilizerToCrop(Long cropId, Long fertilizerId) {

    Optional<Fertilizer> optionalFertilizer = this.fertilizerRepository.findById(fertilizerId);
    if (optionalFertilizer.isEmpty()) {
      throw new FertilizerNotFound();
    }
    Crop crop = this.lookIfCropExist(cropId);
    Fertilizer fertilizer = optionalFertilizer.get();
    crop.setFertilizers(fertilizer);
    this.cropRepository.save(crop);
  }

  /**
   * Gets all fertilizers by crop id.
   *
   * @param id the id
   * @return the all fertilizers by crop id
   */
  public List<Fertilizer> getAllFertilizersByCropId(Long id) {
    Crop crop = this.lookIfCropExist(id);
    return crop.getFertilizers();
  }

  /**
   * Look if crop exist crop.
   *
   * @param id the id
   * @return the crop
   */
  public Crop lookIfCropExist(Long id) {
    Optional<Crop> optionalCrop = this.cropRepository.findById(id);
    if (optionalCrop.isEmpty()) {
      throw new CropNotFound();
    }
    return optionalCrop.get();
  }
}
