package com.example.leituraApplication.service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ConsumoApi {

    private static final String API_URL = "https://gutendex.com/books?search=";

    @Autowired
    private RestTemplate restTemplate;

    public JsonObject buscarLivroPorTitulo(String titulo) {
        try {
            String query = API_URL + titulo.replace(" ", "+");
            String response = restTemplate.getForObject(query, String.class);

            Gson gson = new Gson();
            return gson.fromJson(response, JsonObject.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
