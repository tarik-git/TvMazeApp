package com.tarik.tvmaze.data.remote.dto;

import com.tarik.tvmaze.domain.model.Show;

import java.util.List;

public class ShowDto {
    private String summary;
    private Image image;
    private long averageRuntime;
    private Links links;
    private String premiered;
    private Rating rating;
    private long runtime;
    private long weight;
    private String language;
    private String type;
    private String url;
    private String officialSite;
    private Network network;
    private Schedule schedule;
    private List<String> genres;
    private String name;
    private String ended;
    private long id;
    private Externals externals;
    private long updated;
    private String status;

    public String getSummary() { return summary; }
    public void setSummary(String value) { this.summary = value; }

    public Image getImage() { return image; }
    public void setImage(Image value) { this.image = value; }

    public long getAverageRuntime() { return averageRuntime; }
    public void setAverageRuntime(long value) { this.averageRuntime = value; }

    public Links getLinks() { return links; }
    public void setLinks(Links value) { this.links = value; }

    public String getPremiered() { return premiered; }
    public void setPremiered(String value) { this.premiered = value; }

    public Rating getRating() { return rating; }
    public void setRating(Rating value) { this.rating = value; }

    public long getRuntime() { return runtime; }
    public void setRuntime(long value) { this.runtime = value; }

    public long getWeight() { return weight; }
    public void setWeight(long value) { this.weight = value; }

    public String getLanguage() { return language; }
    public void setLanguage(String value) { this.language = value; }

    public String getType() { return type; }
    public void setType(String value) { this.type = value; }

    public String geturl() { return url; }
    public void seturl(String value) { this.url = value; }

    public String getOfficialSite() { return officialSite; }
    public void setOfficialSite(String value) { this.officialSite = value; }

    public Network getNetwork() { return network; }
    public void setNetwork(Network value) { this.network = value; }

    public Schedule getSchedule() { return schedule; }
    public void setSchedule(Schedule value) { this.schedule = value; }

    public List<String> getGenres() { return genres; }
    public void setGenres(List<String> value) { this.genres = value; }

    public String getName() { return name; }
    public void setName(String value) { this.name = value; }

    public String getEnded() { return ended; }
    public void setEnded(String value) { this.ended = value; }

    public long getid() { return id; }
    public void setid(long value) { this.id = value; }

    public Externals getExternals() { return externals; }
    public void setExternals(Externals value) { this.externals = value; }

    public long getUpdated() { return updated; }
    public void setUpdated(long value) { this.updated = value; }

    public String getStatus() { return status; }
    public void setStatus(String value) { this.status = value; }

    public Show toShow() {
        Show show = new Show();
        if (image != null && image.getMedium() != null) {
            show.posterUrl = image.getMedium();
        }
        if (image != null && image.getOriginal() != null) {
            show.originalPosterUrl = image.getOriginal();
        }
        show.showId = this.id;
        show.showSite = this.officialSite;
        show.showPremiered = this.premiered;
        show.showRating = this.rating.getAverage();
        show.showSummary = this.summary;
        show.showName= this.name;
        return show;
    }

}