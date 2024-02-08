package com.betrybe.agrix.controller;

import com.betrybe.agrix.exceptions.CropNotFound;
import com.betrybe.agrix.exceptions.FarmNotFound;
import com.betrybe.agrix.exceptions.FertilizerNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * The type Gerenciador exception controller.
 */
@ControllerAdvice
public class GerenciadorExceptionController {

  /**
   * Handle farm not found response entity.
   *
   * @param e the e
   * @return the response entity
   */
  @ExceptionHandler(FarmNotFound.class)
  public ResponseEntity<String> handleFarmNotFound(FarmNotFound e) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fazenda não encontrada!");
  }

  /**
   * Handle crop not found response entity.
   *
   * @param e the e
   * @return the response entity
   */
  @ExceptionHandler(CropNotFound.class)
  public ResponseEntity<String> handleCropNotFound(CropNotFound e) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Plantação não encontrada!");
  }

  /**
   * Handle fertilizer not found response entity.
   *
   * @param e the e
   * @return the response entity
   */
  @ExceptionHandler(FertilizerNotFound.class)
  public ResponseEntity<String> handleFertilizerNotFound(FertilizerNotFound e) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fertilizante não encontrado!");
  }

}
