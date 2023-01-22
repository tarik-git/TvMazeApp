package com.tarik.tvmaze.data.remote.dto;

public class ShowResponse {
    private double score;
    private ShowDto show;

    public double getScore() { return score; }
    public void setScore(double value) { this.score = value; }

    public ShowDto getShow() { return show; }
    public void setShow(ShowDto value) { this.show = value; }
}
