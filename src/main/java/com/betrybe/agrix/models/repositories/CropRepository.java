package com.betrybe.agrix.models.repositories;

import com.betrybe.agrix.dto.CropDto;
import com.betrybe.agrix.models.entities.Crop;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * The interface Crop repository.
 */
@Repository
public interface CropRepository extends JpaRepository<Crop, Long> {

  @Query("SELECT c FROM Crop c WHERE c.harvestDate >= ?1 and c.harvestDate <= ?2")
  List<Crop> findByDateBetween(LocalDate start, LocalDate end);

}
