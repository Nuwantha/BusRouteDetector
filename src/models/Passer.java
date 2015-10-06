/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.ArrayList;
import javax.swing.JTextArea;

/**
 *
 * @author DinsuG
 */
public class Passer {

    private ArrayList<String> route_num;
    private ArrayList<String> start_location;
    private String end_location;
    private double bus_fare;
    private double distance;
    private int calling_Time = 0;

    public Passer(ArrayList<String> route_num, ArrayList<String> start_location, String end_location, double bus_fare, double distance) {
        this.route_num = route_num;
        this.start_location = start_location;
        this.end_location = end_location;
        this.bus_fare = bus_fare;
        this.distance = distance;
    }
    
    public int getCalling_Time() {
        return calling_Time;
    }

    public void setCalling_Time(int calling_Time) {
        this.calling_Time = calling_Time;
    }
    
    public ArrayList<String> getRoute_num() {
        return route_num;
    }

    public void setRoute_num(ArrayList<String> route_num) {
        this.route_num = route_num;
    }

    public ArrayList<String> getStart_location() {
        return start_location;
    }

    public void setStart_location(ArrayList<String> start_location) {
        this.start_location = start_location;
    }

    public String getEnd_location() {
        return end_location;
    }

    public void setEnd_location(String end_location) {
        this.end_location = end_location;
    }

    public double getBus_fare() {
        return bus_fare;
    }

    public void setBus_fare(double bus_fare) {
        this.bus_fare = bus_fare;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

}
