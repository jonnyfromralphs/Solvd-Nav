package com.solvd.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class GeocoderService {
    private static final String GEOCODING_RESOURCE = "https://geocode.search.hereapi.com/v1/geocode";
    private static final String API_KEY = System.getenv("API_KEY");

    public String GeocodeSync(String query) throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newHttpClient();
        String encodedQuery = URLEncoder.encode(query,"UTF-8");
        String requestUri = GEOCODING_RESOURCE + "?apiKey=" + API_KEY + "&q=" + encodedQuery;
        HttpRequest geocodingRequest = HttpRequest.newBuilder().GET().uri(URI.create(requestUri))
                .timeout(Duration.ofMillis(2000)).build();

        HttpResponse geocodingResponse = httpClient.send(geocodingRequest,
                HttpResponse.BodyHandlers.ofString());

        return geocodingResponse.body().toString();
    }

    public double parseLatitude(String response) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode responseJsonNode = mapper.readTree(response);
            JsonNode items = responseJsonNode.get("items");
            return items.get(0).get("position").get("lat").asDouble();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public double parseLongitude(String response) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode responseJsonNode = mapper.readTree(response);
            JsonNode items = responseJsonNode.get("items");
            return items.get(0).get("position").get("lng").asDouble();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
