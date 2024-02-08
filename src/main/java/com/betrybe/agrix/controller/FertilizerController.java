package com.betrybe.agrix.controller;

import com.betrybe.agrix.dto.FertilizerCreationDto;
import com.betrybe.agrix.models.entities.Fertilizer;
import com.betrybe.agrix.service.FertilizerService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Fertilizer controller.
 */
@RestController
@RequestMapping(value = "fertilizers")
@Secured({"ADMIN"})
public class FertilizerController {

  @Autowired
  private FertilizerService fertilizerService;

  /**
   * Creating fertilizer response entity.
   *
   * @param fertilizerCreationDto the fertilizer creation dto
   * @return the response entity
   */
  @PostMapping
  public ResponseEntity<Fertilizer> creatingFertilizer(
      @RequestBody() FertilizerCreationDto fertilizerCreationDto) {
    Fertilizer fertilizer = this.fertilizerService.createFertilizer(fertilizerCreationDto);
    return ResponseEntity.status(HttpStatus.CREATED).body(fertilizer);
  }

  /**
   * Gets all fertilizers.
   *
   * @return the all fertilizers
   */
  @GetMapping
  public ResponseEntity<List<Fertilizer>> getAllFertilizers() {
    return ResponseEntity.status(HttpStatus.OK).body(this.fertilizerService.getAllFertilizers());
  }

  /**
   * Gets fertilizer by id.
   *
   * @param id the id
   * @return the fertilizer by id
   */
  @GetMapping("{id}")
  public ResponseEntity<Fertilizer> getFertilizerById(@PathVariable Long id) {
    return ResponseEntity.status(HttpStatus.OK).body(this.fertilizerService.getFertilizerById(id));
  }


}
