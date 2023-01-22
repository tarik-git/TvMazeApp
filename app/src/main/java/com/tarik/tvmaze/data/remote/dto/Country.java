package com.tarik.tvmaze.data.remote.dto;

public class Country {
    private String code;
    private String timezone;
    private String name;

    public String getCode() { return code; }
    public void setCode(String value) { this.code = value; }

    public String getTimezone() { return timezone; }
    public void setTimezone(String value) { this.timezone = value; }

    public String getName() { return name; }
    public void setName(String value) { this.name = value; }
}