package by.general.sensors.repository;

import by.general.sensors.entity.SensorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorRepository extends JpaRepository<SensorEntity, Long>, JpaSpecificationExecutor<SensorEntity> {
}
