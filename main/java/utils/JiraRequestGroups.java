package utils;

import apis.JiraApiUrls;

public class JiraRequestGroups {


    public JiraRequestSender createIssue(String body){
        JiraRequestSender jiraRequestSender = new JiraRequestSender();
        jiraRequestSender.createRequest(body).post(JiraApiUrls.ISSUE.getUri());
        return jiraRequestSender;
    }

    public JiraRequestSender deleteIssue(String issue){
        JiraRequestSender jiraRequestSender = new JiraRequestSender();
        jiraRequestSender.createEmptyRequest().delete(JiraApiUrls.ISSUE.getUri(issue));
        return jiraRequestSender;
    }

    public JiraRequestSender getIssue(String issue){
        JiraRequestSender jiraRequestSender = new JiraRequestSender();
        jiraRequestSender.createEmptyRequest().get(JiraApiUrls.ISSUE.getUri(issue));
        return jiraRequestSender;
    }

    public JiraRequestSender search(String body){
        JiraRequestSender jiraRequestSender = new JiraRequestSender();
        jiraRequestSender.createRequest(body).post(JiraApiUrls.SEARCH.getUri());
        return jiraRequestSender;
    }

    public JiraRequestSender addCommentToIssue(String body, String issue){
        JiraRequestSender jiraRequestSender = new JiraRequestSender();
        jiraRequestSender.createRequest(body).post(JiraApiUrls.ISSUE.getUri(issue+"/comment"));
        return jiraRequestSender;
    }

    public JiraRequestSender authenticate(){
        JiraRequestSender jiraRequestSender = new JiraRequestSender();
        jiraRequestSender.authenticate();
        return jiraRequestSender;
    }

}
