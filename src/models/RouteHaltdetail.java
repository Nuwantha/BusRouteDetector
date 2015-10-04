/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author DinsuG
 */
public class RouteHaltdetail {

    private String routeNo;
    private int haltNo;
    private String haltName;
    private Double distance;
    private Double busFair;

    
    public RouteHaltdetail(String routeNo, int haltNo, String haltName, Double distance, Double busFair) {
        this.routeNo = routeNo;
        this.haltNo = haltNo;
        this.haltName = haltName;
        this.distance = distance;
        this.busFair = busFair;
    }

    public String getRouteNo() {
        return routeNo;
    }

    public void setRouteNo(String routeNo) {
        this.routeNo = routeNo;
    }

    public int getHaltNo() {
        return haltNo;
    }

    public void setHaltNo(int haltNo) {
        this.haltNo = haltNo;
    }

    public String getHaltName() {
        return haltName;
    }

    public void setHaltName(String haltName) {
        this.haltName = haltName;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Double getBusFair() {
        return busFair;
    }

    public void setBusFair(Double busFair) {
        this.busFair = busFair;
    }
    
}
