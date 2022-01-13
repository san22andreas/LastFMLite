package de.mihaly.lastfmlite;

public class ArtistsByID {

    private String url;
    private String playcount;
    private String name;
    private String rank;

    public ArtistsByID(String url, String playcount, String rank, String name) {
        this.url = url;
        this.playcount = playcount;
        this.name = name;
        this.rank = rank;
    }

    public ArtistsByID() {
    }

    @Override
    public String toString() {
        return "Rank: " + rank +
                "\n" + name +
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
}

