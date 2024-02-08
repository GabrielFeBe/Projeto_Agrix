package com.betrybe.agrix.controller;


import com.betrybe.agrix.dto.CropCreationDto;
import com.betrybe.agrix.dto.CropDto;
import com.betrybe.agrix.dto.FarmDto;
import com.betrybe.agrix.dto.FarmDto.ResponseDto;
import com.betrybe.agrix.models.entities.Farm;
import com.betrybe.agrix.service.FarmService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Farm controller.
 */
@RestController
@RequestMapping(value = "/farms")
public class FarmController {

  private FarmService farmService;

  /**
   * Instantiates a new Farm controller.
   *
   * @param farmService the farm service
   */
  @Autowired
  public FarmController(FarmService farmService) {
    this.farmService = farmService;
  }

  /**
   * Insert farm response entity.
   *
   * @param farmDto the farm dto
   * @return the response entity
   */
  @PostMapping
  public ResponseEntity<Farm> insertFarm(@RequestBody FarmDto farmDto) {
    Farm farm = this.farmService.insertFarm(farmDto.dtoToFarm());
    return ResponseEntity.status(HttpStatus.CREATED).body(farm);
  }

  /**
   * Gets all farms.
   *
   * @return the all farms
   */
  @GetMapping
  public ResponseEntity<List<FarmDto>> getAllFarms() {
    var farm = this.farmService.getAllFarms();
    List<FarmDto> farms = farm.stream()
        .map(farm1 -> new FarmDto(farm1.getId(), farm1.getName(), farm1.getSize()))
        .toList();
    return ResponseEntity.status(HttpStatus.OK).body(farms);
  }

  /**
   * Gets farm by id.
   *
   * @param id the id
   * @return the farm by id
   */
  @GetMapping("/{id}")
  public ResponseEntity<FarmDto> getFarmById(@PathVariable Long id) {
    Farm farm = this.farmService.getFarmById(id);
    FarmDto farmDto = new FarmDto(farm.getId(), farm.getName(), farm.getSize());
    return ResponseEntity.status(HttpStatus.OK).body(farmDto);
  }

  /**
   * Insert crop response entity.
   *
   * @param cropCreationDto the crop creation dto
   * @param farmId          the farm id
   * @return the response entity
   */
  @PostMapping("/{farmId}/crops")
  public ResponseEntity<CropDto> insertCrop(@RequestBody CropCreationDto cropCreationDto,
      @PathVariable Long farmId
  ) {
    var crop = this.farmService.insertCrop(farmId, cropCreationDto);
    return ResponseEntity.status(HttpStatus.CREATED).body(new CropDto(
        crop.getId(), crop.getFarm().getId(), crop.getName(), crop.getPlantedArea(),
        crop.getPlantedDate(), crop.getHarvestDate()
    ));
  }

  /**
   * Gets crops by farm id.
   *
   * @param farmId the farm id
   * @return the crops by farm id
   */
  @GetMapping("/{farmId}/crops")
  public ResponseEntity<List<CropDto>> getCropsByFarmId(
      @PathVariable Long farmId
  ) {
    var crops = this.farmService.getCropByFarmId(farmId);
    return ResponseEntity.status(HttpStatus.OK).body(crops.stream().map(crop ->
        new CropDto(crop.getId(), farmId, crop.getName(), crop.getPlantedArea(),
            crop.getPlantedDate(), crop.getHarvestDate())
    ).toList());
  }

}
