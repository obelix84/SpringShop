package ru.gb.application.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.gb.application.repository.GoodRepository;

import java.math.BigInteger;

@Endpoint
public class InfoGoodEndpoint {

    private static final String NAMESPACE_URL = "http://gb.ru/application/web/service";
    private GoodRepository goodRepository;

    @Autowired
    public InfoGoodEndpoint(GoodRepository goodRepository) {
        this.goodRepository = goodRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URL, localPart = "getGoodRequest")
    @ResponsePayload
    public GetGoodResponse getInfoResponse(@RequestPayload GetGoodRequest request) {
        GetGoodResponse response = new GetGoodResponse();
        response.setGood(createGoodResponse(request.getId()));
        return response;
    }

    private Good createGoodResponse(BigInteger id) {
        //упрощенная обработка
        //предполагает, что такой объект есть
        ru.gb.application.entity.Good good = this.goodRepository.findById(id.longValue()).get();
        Good goodResponse = new Good();
        goodResponse.setId(BigInteger.valueOf(good.getId()));
        goodResponse.setName(good.getName());
        goodResponse.setPrice(good.getPrice());
        return goodResponse;
    }
}
