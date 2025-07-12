package com.ecom.productcatalogue.repository;

import com.ecom.productcatalogue.repository.dto.FakeStoreProductDto;
import com.ecom.productcatalogue.repository.dto.UserTokenDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
public class UserRepository {
    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    public Boolean validateToken(String token) {
        if (token == null || token.isEmpty()) {
            return false;
        }
        UserTokenDto dto = UserTokenDto.builder()
                .token(token)
                .build();

        ResponseEntity<Boolean> validTokenResponseEntity =
                requestForEntity("http://localhost:9000/api/validateToken", HttpMethod.POST, dto, Boolean.class, null);

        Boolean isTokenValid = validTokenResponseEntity.getBody();
        if(validTokenResponseEntity.getStatusCode().equals(HttpStatusCode.valueOf(200)) &&
                isTokenValid!=null) {
            return isTokenValid;
        }

        return false;
    }

    private <T> ResponseEntity<T> requestForEntity(String url, HttpMethod httpMethod, @Nullable Object request, Class<T> responseType, Object... uriVariables) throws RestClientException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        Object[] safeUriVariables = (uriVariables == null) ? new Object[]{} : uriVariables;
        return restTemplate.execute(url, httpMethod, requestCallback, responseExtractor, safeUriVariables);
    }
}