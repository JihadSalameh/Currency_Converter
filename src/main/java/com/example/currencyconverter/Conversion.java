package com.example.currencyconverter;

public class Conversion {

    private double value;
    private String from;
    private String to;

    public Conversion(double value, String from, String to) {
        this.value = value;
        this.from = from;
        this.to = to;
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

    public String toString() {
        return "Value: " + value + " From: " + from + " To: " + to;
    }

}
