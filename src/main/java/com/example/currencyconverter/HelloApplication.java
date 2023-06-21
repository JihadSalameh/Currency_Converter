package com.example.currencyconverter;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;

public class HelloApplication extends Application {

    private static ArrayList<String> currencies;

    @FXML
    private ComboBox<String> toCombo = new ComboBox<>();

    @FXML
    private Label resultLbl;

    @FXML
    private TextField amountTxt;

    @FXML
    private ComboBox<String> fromCombo = new ComboBox<>();

    @FXML
    private Label rateLbl;

    @FXML
    void getFromCurrencies(Event event) {
        ObservableList<String> list = FXCollections.observableArrayList(currencies);
        fromCombo.setItems(list);
    }

    @FXML
    void getToCurrencies(Event event) {
        ObservableList<String> list = FXCollections.observableArrayList(currencies);
        toCombo.setItems(list);
    }

    public void Convert(ActionEvent actionEvent) {
        if(!Objects.equals(fromCombo.getValue(), null) && !Objects.equals(toCombo.getValue(), null) && !Objects.equals(amountTxt.getText(), "")) {
            String from = fromCombo.getValue();
            String to = toCombo.getValue();
            String amount = amountTxt.getText();
            apiConnection(from, to, amount);
        } else {
            rateLbl.setText("Please fill all fields");
            resultLbl.setText("Please fill all fields");
        }
    }

    @Override
    public void start(Stage stage) throws IOException {
        AnchorPane root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("hello-view.fxml")));        Scene scene = new Scene(root);
        stage.setTitle("Currency Converter");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        setupCurrencies();
        launch();
    }

    private void apiConnection(String from, String to, String amount) {
        // Setting URL
        String url_str = "https://v6.exchangerate-api.com/v6/8814c1712af4e89412c13977/pair/" + from + "/" + to;

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
            String val =  json.getString("conversion_rate");

            double rate = Double.parseDouble(val);
            double amount1 = Double.parseDouble(amount);

            calculate(rate, amount1, val);

            // Close the connection
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void calculate(double rate, double amount1, String val) {
        amount1 *= rate;
        rateLbl.setText(val);
        resultLbl.setText(String.valueOf(amount1));
    }

    private static void setupCurrencies() {
        currencies = new ArrayList<>();
        currencies.add("USD");
        currencies.add("JOD");
        currencies.add("ILS");
        currencies.add("AED");
        currencies.add("CNY");
        currencies.add("EGP");
        currencies.add("EUR");
        currencies.add("GBP");
        currencies.add("RUB");
        currencies.add("SAR");
    }

}