package apis;

public enum JiraApiUrls {

    LOGIN("/rest/auth/1/session"),
    ISSUE("/rest/api/2/issue"),
    SEARCH("/rest/api/2/search"), ;

    private String uri;

    JiraApiUrls(String url) {
        this.uri = url;
    }

    public String getUri() {
        return this.uri;
    }

    public String getUri(String suffix) {
        return this.uri + "/" + suffix;
    }

}
