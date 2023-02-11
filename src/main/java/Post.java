import com.fasterxml.jackson.annotation.JsonProperty;

public class Post {
    private final String copyright;
    private final String date;
    private final String explanation;
    private final String hdurl;
    private final String mediaType;
    private final String serviceVersion;
    private final String title;
    private final String url;
    public Post(
            @JsonProperty("copyright") String copyright,
            @JsonProperty("date") String date,
            @JsonProperty("explanation") String explanation,
            @JsonProperty("hdurl") String hdurl,
            @JsonProperty("media_type") String mediaType,
            @JsonProperty("service_version") String serviceVersion,
            @JsonProperty("title") String title,
            @JsonProperty("url") String url
    ) {
        this.copyright = copyright;
        this.date = date;
        this.explanation = explanation;
        this.hdurl = hdurl;
        this.mediaType = mediaType;
        this.serviceVersion = serviceVersion;
        this.title = title;
        this.url = url;
    }

    public String getHdurl() {
        return hdurl;
    }

    @Override
    public String toString() {
        return "\n" +
                "\n  copyright : " + copyright+
                "\n  date : " + date +
                "\n  explanation : " + explanation +
                "\n  hdurl : " + hdurl +
                "\n  media-type : " + mediaType +
                "\n  service-version : " + serviceVersion +
                "\n  title : " + title +
                "\n  url : " + url;
    }
}
