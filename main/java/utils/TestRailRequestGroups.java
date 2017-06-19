package utils;

import apis.JiraApiUrls;

public class TestRailRequestGroups {


    public TestRailRequestSender createIssue(String body){
        TestRailRequestSender TestRailRequestSender = new TestRailRequestSender();
        TestRailRequestSender.createRequest(body).post(JiraApiUrls.ISSUE.getUri());
        return TestRailRequestSender;
    }

    public TestRailRequestSender deleteIssue(String issue){
        TestRailRequestSender TestRailRequestSender = new TestRailRequestSender();
        TestRailRequestSender.createEmptyRequest().delete(JiraApiUrls.ISSUE.getUri(issue));
        return TestRailRequestSender;
    }

    public TestRailRequestSender getIssue(String issue){
        TestRailRequestSender TestRailRequestSender = new TestRailRequestSender();
        TestRailRequestSender.createEmptyRequest().get(JiraApiUrls.ISSUE.getUri(issue));
        return TestRailRequestSender;
    }

    public TestRailRequestSender search(String body){
        TestRailRequestSender TestRailRequestSender = new TestRailRequestSender();
        TestRailRequestSender.createRequest(body).post(JiraApiUrls.SEARCH.getUri());
        return TestRailRequestSender;
    }

    public TestRailRequestSender addCommentToIssue(String body, String issue){
        TestRailRequestSender TestRailRequestSender = new TestRailRequestSender();
        TestRailRequestSender.createRequest(body).post(JiraApiUrls.ISSUE.getUri(issue+"/comment"));
        return TestRailRequestSender;
    }

    public TestRailRequestSender authenticate(){
        TestRailRequestSender TestRailRequestSender = new TestRailRequestSender();
        TestRailRequestSender.authenticate();
        return TestRailRequestSender;
    }

}
