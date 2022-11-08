package by.general.sensors.repository.specification;

import by.general.sensors.entity.SensorEntity;
import by.general.sensors.entity.TypeEntity;
import by.general.sensors.entity.UnitEntity;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Join;

public class SensorSpecificationFactory {

    public SensorSpecificationFactory() {
    }

    public static Specification<SensorEntity> getSensorsByLikeDescription(String description) {
        return getSensorsByLikeStringField(SensorProperties.DESCRIPTION, description);
    }

    public static Specification<SensorEntity> getMonitorSensorsByLikeLocation(String location) {
        return getSensorsByLikeStringField(SensorProperties.LOCATION, location);
    }

    public static Specification<SensorEntity> getSensorsByLikeModel(String model) {
        return getSensorsByLikeStringField(SensorProperties.MODEL, model);
    }

    public static Specification<SensorEntity> getSensorsByLikeName(String name) {
        return getSensorsByLikeStringField(SensorProperties.NAME, name);
    }


    public static Specification<SensorEntity> getSensorsByLikeType(String type) {
        return (root, query, criteriaBuilder) -> {
            Join<SensorEntity, TypeEntity> sensorEntityTypeEntityJoin = root.join(SensorProperties.TYPE);
            return criteriaBuilder.like(criteriaBuilder.lower(sensorEntityTypeEntityJoin.get(SensorProperties.NAME)), getContainsLikePattern(type).toLowerCase());
        };
    }

    public static Specification<SensorEntity> getSensorsByLikeUnit(String unit) {
        return (root, query, criteriaBuilder) -> {
            Join<SensorEntity, UnitEntity> sensorEntityUnitEntityJoin = root.join(SensorProperties.UNIT);
            return criteriaBuilder.like(criteriaBuilder.lower(sensorEntityUnitEntityJoin.get(SensorProperties.NAME)), getContainsLikePattern(unit).toLowerCase());
        };
    }


    private static Specification<SensorEntity> getSensorsByLikeStringField(String nameField, String value) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.lower(root.get(nameField)), getContainsLikePattern(value).toLowerCase());
    }

    private static String getContainsLikePattern(String searchTerm) {
        if (searchTerm == null || searchTerm.isEmpty()) {
            return "%";
        } else {
            return "%" + searchTerm + "%";
        }
    }
    
}
