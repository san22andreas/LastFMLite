package de.mihaly.lastfmlite;

public class TracksByID {
    private String url;
    private String playcount;
    private String name;
    private String name_artist;
    private String rank;


    public TracksByID(String url, String playcount, String rank, String name, String name_artist) {
        this.url = url;
        this.playcount = playcount;
        this.name = name;
        this.rank = rank;
        this.name_artist = name_artist;

    }

    public TracksByID() {
    }

    @Override
    public String toString() {
        return "Rank: " + rank +
                "\n" + name +
                "\n" + name_artist +
                "\nScrobbles: "  + playcount;

    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPlaycount() {
        return playcount;
    }

    public void setPlaycount(String playcount) {
        this.playcount = playcount;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getName_artist() {
        return name_artist;
    }

    public void setName_artist(String name_artist) {
        this.name_artist = name_artist;
    }
}

