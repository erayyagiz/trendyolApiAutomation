package com.trendyol.apiautomation.model;


public class Result {
    private String result;
    private String error;
    Result() {
    }

    static Result withResult(String resultMessage) {
        Result apiResult = new Result();
        apiResult.result = resultMessage;
        return apiResult;
    }

    static Result withError(String errorMessage) {
        Result apiResult = new Result();
        apiResult.error = errorMessage;
        return apiResult;
    }


    public String getResult() {
        return result;
    }

    public String getError() {
        return error;
    }

}
