package com.example.currencyconverter;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Conversion {

    private double value;
    private String from;
    private String to;

    private double rate;

    public Conversion(double value, String from, String to) {
        this.value = value;
        this.from = from;
        this.to = to;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public double getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public double apiConnection() {
        // Setting URL
        String url_str = "https://v6.exchangerate-api.com/v6/8814c1712af4e89412c13977/pair/" + from + "/" + to;
        double ans = 0;

        try {
            // Create URL object
            URL url = new URL(url_str);

            // Create HttpURLConnection
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            // Set request method
            conn.setRequestMethod("GET");

            // Read the response body as JSON
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line).append("\n");
            }
            reader.close();

            JSONObject json = new JSONObject(response.toString());
            String r =  json.getString("conversion_rate");

            rate = Double.parseDouble(r);

            ans = calculate(rate, value);

            // Close the connection
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ans;
    }

    public double calculate(double rate, double amount1) {
        amount1 *= rate;
        return amount1;
    }

    @Override
    public String toString() {
        return "Value: " + value + " From: " + from + " To: " + to;
    }

}
