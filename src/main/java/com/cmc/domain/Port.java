package com.cmc.domain;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "tbl_port")
@ToString
@Setter
@Getter
@EqualsAndHashCode
public class Port {


    @Id
    private String portKey;

    @Column
    private String code;

    @Column
    private String timezone;

    @Column
    private String province;

    @Column
    private String country;

    @Column
    private String city;

    @Column
    private String name;

    @Column
    private String alias;

    @Column
    private String region;

    @Column
    private String unloc;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "coordinate_id")
    private Coordinate coordinate;
}
