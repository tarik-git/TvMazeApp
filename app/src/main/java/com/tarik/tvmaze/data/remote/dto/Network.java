package com.tarik.tvmaze.data.remote.dto;

public class Network {
    private Country country;
    private String name;
    private long id;
    private String officialSite;

    public Country getCountry() { return country; }
    public void setCountry(Country value) { this.country = value; }

    public String getName() { return name; }
    public void setName(String value) { this.name = value; }

    public long getid() { return id; }
    public void setid(long value) { this.id = value; }

    public String getOfficialSite() { return officialSite; }
    public void setOfficialSite(String value) { this.officialSite = value; }
}