package apis;

public enum TestRailApiUrls {

    BASE("http://blala");

    private String uri;

    TestRailApiUrls(String url) {
        this.uri = url;
    }

    public String getUri() {
        return this.uri;
    }

    public String getUri(String suffix) {
        return this.uri + "/" + suffix;
    }
}
