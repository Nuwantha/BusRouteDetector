/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author Gimhani
 */
public class BusRoute {
    private String route_num;
    private String start_location;
    private String end_location;
    private double bus_fare;
    private double distance;

    public BusRoute(String route_num, String start_location, String end_location, double bus_fare, double distance) {
        this.route_num = route_num;
        this.start_location = start_location;
        this.end_location = end_location;
        this.bus_fare = bus_fare;
        this.distance = distance;
    }

    public String getRoute_num() {
        return route_num;
    }

    public void setRoute_num(String route_num) {
        this.route_num = route_num;
    }

    public String getStart_location() {
        return start_location;
    }

    public void setStart_location(String start_location) {
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
