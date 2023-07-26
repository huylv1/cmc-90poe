package com.cmc.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class PortVO {
    @JsonProperty("name")
    public String getName() {
        return this.name; }
    public void setName(String name) {
        this.name = name; }
    String name;
    @JsonProperty("city")
    public String getCity() {
        return this.city; }
    public void setCity(String city) {
        this.city = city; }
    String city;
    @JsonProperty("country")
    public String getCountry() {
        return this.country; }
    public void setCountry(String country) {
        this.country = country; }
    String country;
    @JsonProperty("alias")
    public ArrayList<String> getAlias() {
        return this.alias; }
    public void setAlias(ArrayList<String> alias) {
        this.alias = alias; }
    ArrayList<String> alias;
    @JsonProperty("regions")
    public ArrayList<String> getRegions() {
        return this.regions; }
    public void setRegions(ArrayList<String> regions) {
        this.regions = regions; }
    ArrayList<String> regions;
    @JsonProperty("coordinates")
    public ArrayList<Double> getCoordinates() {
        return this.coordinates; }
    public void setCoordinates(ArrayList<Double> coordinates) {
        this.coordinates = coordinates; }
    ArrayList<Double> coordinates;
    @JsonProperty("province")
    public String getProvince() {
        return this.province; }
    public void setProvince(String province) {
        this.province = province; }
    String province;
    @JsonProperty("timezone")
    public String getTimezone() {
        return this.timezone; }
    public void setTimezone(String timezone) {
        this.timezone = timezone; }
    String timezone;
    @JsonProperty("unlocs")
    public ArrayList<String> getUnlocs() {
        return this.unlocs; }
    public void setUnlocs(ArrayList<String> unlocs) {
        this.unlocs = unlocs; }
    ArrayList<String> unlocs;
    @JsonProperty("code")
    public String getCode() {
        return this.code; }
    public void setCode(String code) {
        this.code = code; }
    String code;

    @Override
    public String toString() {
        return "PortVO{" +
                "name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", alias=" + alias +
                ", regions=" + regions +
                ", coordinates=" + coordinates +
                ", province='" + province + '\'' +
                ", timezone='" + timezone + '\'' +
                ", unlocs=" + unlocs +
                ", code='" + code + '\'' +
                '}';
    }
}
