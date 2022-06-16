package com.trendyol.apiautomation.requests;
import com.trendyol.apiautomation.config.ConfigReader;
import com.trendyol.apiautomation.endpoints.Endpoints;
import org.junit.jupiter.api.Test;


class ApiAutomationApplicationTests extends Endpoints {

    ConfigReader configReader = new ConfigReader();
    String userJSON = configReader.readJsonFile();

    @Test
    public void addParams() {
        String authToken = postLogin(userJSON);
        add(authToken,"1,2,3,4");
    }

    @Test
    public void subtractParams() {
        String authToken = postLogin(userJSON);
        subtract(authToken,"10,5");
    }

    @Test
    public void multiplyParams() {
        String authToken = postLogin(userJSON);
        multiplication(authToken,"4,5,8");
    }

    @Test
    public void divisionParams() {
        String authToken = postLogin(userJSON);
        division(authToken,"4,5");
    }

    @Test
    public void sumToParam() {
        String authToken = postLogin(userJSON);
        sum(authToken,"5");
    }

}
