package com.cmc.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;

@ToString
@Getter
@Setter
public class PortVO {
    @JsonProperty("name")
    String name;
    @JsonProperty("city")
    String city;
    @JsonProperty("country")
    String country;
    @JsonProperty("alias")
    ArrayList < String > alias;
    @JsonProperty("regions")
    ArrayList < String > regions;
    @JsonProperty("coordinates")
    ArrayList < Double > coordinates;

    @JsonProperty("province")
    String province;

    @JsonProperty("timezone")
    String timezone;

    @JsonProperty("unlocs")
    ArrayList < String > unlocs;

    @JsonProperty("code")
    String code;

}