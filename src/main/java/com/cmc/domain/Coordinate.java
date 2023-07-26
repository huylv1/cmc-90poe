package com.cmc.domain;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "tbl_coordinate")
@ToString
@Getter
@Setter
@EqualsAndHashCode
public class Coordinate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private Double latitude;
    private Double longitude;

    public long getId() {
        return id;
    }


}
