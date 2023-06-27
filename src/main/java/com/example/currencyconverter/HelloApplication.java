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
            Conversion conversion = new Conversion(Double.parseDouble(amount), from, to);
            double ans = conversion.apiConnection();
            rateLbl.setText(String.valueOf(conversion.getRate()));
            resultLbl.setText(String.valueOf(ans));
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