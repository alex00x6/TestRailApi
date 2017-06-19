package utils;

import apis.JiraApiUrls;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;
import inputData.JiraGenerateJSON;

import static com.jayway.restassured.RestAssured.given;

public class TestRailRequestSender {
    private static String tr_session = null;
    private static String STUDIO_TOKEN = null;
    private final static ContentType CONTENT_TYPE = ContentType.JSON;
    private RequestSpecification requestSpecification = null;
    public Response response = null;

    TestRailRequestSender(){
    }

    void authenticate(){
        JiraGenerateJSON generateJSON = new JiraGenerateJSON();
        String credentials = generateJSON.login();

        createRequest(credentials)
                .post(JiraApiUrls.LOGIN.getUri());


        if (RestAssured.baseURI.contains("https://")){
            System.out.println("Connected via HTTPS");

            tr_session = response.then().extract().path("session.value");
            STUDIO_TOKEN = response.then().extract().cookie("studio.crowd.tokenkey");
            System.out.println(tr_session);
            System.out.println(STUDIO_TOKEN);
        }
        else{
            System.out.println("Connected via HTTP");

            tr_session = response.then().extract().path("session.value");
            System.out.println(tr_session);
        }

    }

    TestRailRequestSender createRequest(String body){
        if (RestAssured.baseURI.contains("https://")){
            this.createRequestSpecification()
                    .addHeader("Content-Type", CONTENT_TYPE.toString())
                    .addHeader("Cookie", "JSESSIONID="+ TestRailRequestSender.tr_session)
                    .addHeader("Cookie", "studio.crowd.tokenkey="+ TestRailRequestSender.STUDIO_TOKEN)
                    .addBody(body);
            return this;
        }
        else{
            this.createRequestSpecification()
                    .addHeader("Content-Type", CONTENT_TYPE.toString())
                    .addHeader("Cookie", "JSESSIONID=" + TestRailRequestSender.tr_session)
                    .addBody(body);
            return this;
        }
    }


    TestRailRequestSender createEmptyRequest(){
        if(RestAssured.baseURI.contains("https://")){
            this.createRequestSpecification()
                    .addHeader("Content-Type", CONTENT_TYPE.toString())
                    .addHeader("Cookie", "JSESSIONID="+ TestRailRequestSender.tr_session)
                    .addHeader("Cookie", "studio.crowd.tokenkey="+ TestRailRequestSender.STUDIO_TOKEN);
            return this;
        }

        else{
            this.createRequestSpecification()
                    .addHeader("Content-Type", CONTENT_TYPE.toString())
                    .addHeader("Cookie", "JSESSIONID=" + TestRailRequestSender.tr_session);
            return this;
        }
    }


    private TestRailRequestSender createRequestSpecification() {
        requestSpecification = given().
                when();
        return this;
    }

    // этот метод сможет добавлять столько угодно хедеров
    private TestRailRequestSender addHeader(String headerName, String headerValue) {
        requestSpecification.header(headerName, headerValue);
        return this;
    }

    private TestRailRequestSender addBody(String body) {
        requestSpecification.body(body);
        return this;
    }

    TestRailRequestSender post(String uri) {
        response = requestSpecification.post(uri);
        return this;
    }

    TestRailRequestSender delete(String uri){
        response = requestSpecification.delete(uri);
        return this;
    }

    TestRailRequestSender get(String uri){
        response = requestSpecification.get(uri);
        return this;
    }

    TestRailRequestSender put(String uri) {
        response = requestSpecification.put(uri);
        return this;
    }

    public String extractResponseByPath(String path){
        return response.then().extract().path(path);
    }

    public String extractAllResponseAsString(){
        return response.then().extract().asString();
    }


}
