package com.tarik.tvmaze.data.remote.dto;

public class Externals {
    private long thetvdb;
    private String imdb;
    private long tvrage;

    public long getThetvdb() { return thetvdb; }
    public void setThetvdb(long value) { this.thetvdb = value; }

    public String getImdb() { return imdb; }
    public void setImdb(String value) { this.imdb = value; }

    public long getTvrage() { return tvrage; }
    public void setTvrage(long value) { this.tvrage = value; }
}