package com.betrybe.agrix.controller;

import com.betrybe.agrix.dto.CropDto;
import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.models.entities.Fertilizer;
import com.betrybe.agrix.service.CropsService;
import jakarta.websocket.server.PathParam;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Crop controller.
 */
@RestController
@RequestMapping(value = "/crops")
@Secured({"MANAGER", "ADMIN"})
public class CropController {

  private CropsService cropsService;

  /**
   * Instantiates a new Crop controller.
   *
   * @param cropsService the crops service
   */
  @Autowired
  public CropController(CropsService cropsService) {
    this.cropsService = cropsService;
  }

  /**
   * Gets all crops.
   *
   * @return the all crops
   */
  @GetMapping
  public ResponseEntity<List<CropDto>> getAllCrops() {
    List<CropDto> cropDtoList = this.cropsService.getAllCrops().stream().map(
        crop -> new CropDto(crop.getId(), crop.getFarm().getId(), crop.getName(),
            crop.getPlantedArea(), crop.getPlantedDate(), crop.getHarvestDate())).toList();
    return ResponseEntity.status(HttpStatus.OK).body(cropDtoList);
  }

  /**
   * Gets crop by id.
   *
   * @param id the id
   * @return the crop by id
   */
  @GetMapping("/{id}")
  public ResponseEntity<CropDto> getCropById(@PathVariable Long id) {
    Crop crop = this.cropsService.getCropById(id);
    return ResponseEntity.status(HttpStatus.OK).body(
        new CropDto(crop.getId(), crop.getFarm().getId(), crop.getName(), crop.getPlantedArea(),
            crop.getPlantedDate(), crop.getHarvestDate()));
  }

  /**
   * Gets crop by date.
   *
   * @param start the start
   * @param end   the end
   * @return the crop by date
   */
  @GetMapping("/search")
  public ResponseEntity<List<CropDto>> getCropByDate(@PathParam("start") LocalDate start,
      @PathParam("end") LocalDate end) {
    var crops = this.cropsService.getByDate(start, end);
    List<CropDto> cropsDto = crops.stream().map(crop -> new CropDto(
        crop.getId(), crop.getFarm().getId(), crop.getName(), crop.getPlantedArea(),
        crop.getPlantedDate(), crop.getHarvestDate()
    )).toList();
    return ResponseEntity.status(HttpStatus.OK).body(cropsDto);
  }

  /**
   * Relate fertilizer to crop response entity.
   *
   * @param cropId       the crop id
   * @param fertilizerId the fertilizer id
   * @return the response entity
   */
  @PostMapping("{cropId}/fertilizers/{fertilizerId}")
  public ResponseEntity<String> relateFertilizerToCrop(@PathVariable Long cropId,
      @PathVariable Long fertilizerId) {
    this.cropsService.relateFertilizerToCrop(cropId, fertilizerId);
    return ResponseEntity.status(HttpStatus.CREATED)
        .body("Fertilizante e plantação associados com sucesso!");
  }

  /**
   * Gets fertilizers by crop id.
   *
   * @param cropId the crop id
   * @return the fertilizers by crop id
   */
  @GetMapping("{cropId}/fertilizers")
  public ResponseEntity<List<Fertilizer>> getFertilizersByCropId(@PathVariable Long cropId
  ) {
    List<Fertilizer> fertilizers = this.cropsService.getAllFertilizersByCropId(cropId);
    return ResponseEntity.status(HttpStatus.OK)
        .body(fertilizers);
  }

}
