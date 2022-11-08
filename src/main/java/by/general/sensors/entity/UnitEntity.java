package by.general.sensors.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "units")
public class UnitEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true, nullable = false)
    private String name;
}
