package com.tarik.tvmaze.data.remote.dto;

import java.util.List;

public class Schedule {
    private List<String> days;
    private String time;

    public List<String> getDays() { return days; }
    public void setDays(List<String> value) { this.days = value; }

    public String getTime() { return time; }
    public void setTime(String value) { this.time = value; }
}
