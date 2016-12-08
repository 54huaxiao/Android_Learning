package com.example.vincent.lab9;

/**
 * Created by Vincent on 2016/11/28.
 */

public class Weather {
    private String date;
    private String weather_description;
    private String temperature;
    public String getDate() {
        return date;
    }
    public String getWeather_description() {
        return weather_description;
    }
    public String getTemperature() {
        return temperature;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public void setWeather_description(String weather_description) {
        this.weather_description = weather_description;
    }
    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }
}
