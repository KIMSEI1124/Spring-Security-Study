package com.openapi.weather;

import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.TimeZone;


@RequiredArgsConstructor
@RestController
public class ApiController {
    private final ApiService apiService;
    @Value("${api.key}")
    private String key;
    @Value("${open.weather.q}")
    private String q;
    @Value("${open.weather.lang}")
    private String lang;
    @Value("${open.weather.units}")
    private String units;

    @GetMapping("/api")
    public void api() {
        String result;
        try {
            URL url = new URL("https://api.openweathermap.org/data/2.5/weather?" +
                    "q=Seoul&" +
                    "lang=kr&" +
                    "units=metric&" +
                    "appid=" + key);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-type", "application/json");

            try (BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(), StandardCharsets.UTF_8))) {
                result = br.readLine();
            }

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(result);

            System.out.println(jsonObject);
            System.out.println(jsonObject.get("dt") instanceof Long);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @GetMapping("/api2")
    public void api2() throws ParseException {
        WebClient webClient = WebClient
                .builder()
                .baseUrl("https://api.openweathermap.org/data/2.5/weather?")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();


        String response = webClient.get().uri(uriBuilder -> uriBuilder
                        .queryParam("q", q)
                        .queryParam("lang", lang)
                        .queryParam("units", units)
                        .queryParam("appid", key)
                        .build())
                .retrieve()
                .bodyToMono(String.class)
                .block();

        JSONParser jsonParser = new JSONParser();

        JSONObject jsonObject = (JSONObject) jsonParser.parse(response);
        JSONArray weatherArray = (JSONArray) jsonObject.get("weather");
        JSONObject weather = (JSONObject) weatherArray.get(0);
        JSONObject main = (JSONObject) jsonObject.get("main");

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH");
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH");
        dateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));

        double temp = (double) main.get("temp");
        long id = (long) weather.get("id");
        LocalDateTime date = LocalDateTime.parse(dateFormat.format((long) jsonObject.get("dt") * 1000), format);

        apiService.save(id, temp, date);
    }

    @GetMapping("/get")
    public ResponseEntity<List<Weather>> get() {
        List<Weather> weathers = apiService.find();
        System.out.println(weathers.size());
        System.out.println(weathers.get(0).getDate());
        return ResponseEntity.ok(weathers);
    }
}
