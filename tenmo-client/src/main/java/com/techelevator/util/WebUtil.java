package com.techelevator.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techelevator.tenmo.model.ApiResponseError;
import org.springframework.web.client.RestClientResponseException;

public class WebUtil {

    public static String getErrorMsgFromRestClientException(RestClientResponseException ex) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            ApiResponseError responseError = mapper.readValue(ex.getResponseBodyAsString(), ApiResponseError.class);
            return responseError.getMessage();
        } catch (JsonProcessingException e) {
            return null;
        }
    }
    public static ApiResponseError getApiResponseErrorFromRestClientException(RestClientResponseException ex) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(ex.getResponseBodyAsString(), ApiResponseError.class);
        } catch (JsonProcessingException e) {
            return null;
        }
    }
}
