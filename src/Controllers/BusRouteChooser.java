/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.BusRoute;
import models.Parser;
import models.RouteHaltdetail;

/**
 *
 * @author DinsuG
 */
public class BusRouteChooser {

    public void getSuitablePath(Parser parser) {
        try {
            // TODO code application logic here
            ArrayList<BusRoute> directBusRoute = PathChooseController.getDirectBusRoute(parser.getStart_location().get(parser.getStart_location().size() - 1), parser.getEnd_location());
            if (directBusRoute != null) {
                System.out.println("open");
                ArrayList<String> route_num = parser.getRoute_num();
                for (String route_num1 : route_num) {
                    BusRoute busRouteDetail = PathChooseController.getBusRouteDetail(route_num1);
                    System.out.println(busRouteDetail.getRoute_num() + "," + busRouteDetail.getStart_location() + "," + busRouteDetail.getEnd_location());

                }
                for (BusRoute directBusRoute1 : directBusRoute) {
                    System.out.println(directBusRoute1.getRoute_num() + "," + directBusRoute1.getStart_location() + "," + directBusRoute1.getEnd_location());
                    System.out.println("Distance : " + (parser.getDistance() + directBusRoute1.getDistance()));
                    System.out.println("BusFair : " + (parser.getBus_fare() + directBusRoute1.getBus_fare()));
                }

                System.out.println("close");
                return;
            } else {
                directBusRoute = PathChooseController.getDirectBusRoute(parser.getEnd_location(), parser.getStart_location().get(parser.getStart_location().size() - 1));
                //System.out.println("2 athula");
                if (directBusRoute != null) {
                    for (BusRoute directBusRoute1 : directBusRoute) {
                        System.out.println(directBusRoute1.getRoute_num() + "," + directBusRoute1.getEnd_location() + "," + directBusRoute1.getStart_location() + "," + directBusRoute1.getBus_fare() + "," + directBusRoute1.getDistance());
                    }
                } else {
                    ArrayList<String> routeNumbers = PathChooseController.getRouteNumbers(parser.getStart_location().get(parser.getStart_location().size() - 1));
                    if (routeNumbers != null) {
                        //get all route start from galle                
                        for (String routeNumber : routeNumbers) {
                            //System.out.println("route nuimber" +routeNumber +"  "+parser.getStart_location().get(parser.getStart_location().size() - 1));
                            ArrayList<RouteHaltdetail> suitableHalt = PathChooseController.getSuitableHalt(routeNumber, parser.getEnd_location());
                            if (suitableHalt != null) {
                                ArrayList<String> route_num = parser.getRoute_num();
                                for (String route_num1 : route_num) {
                                    BusRoute busRouteDetail = PathChooseController.getBusRouteDetail(route_num1);
                                    System.out.println(busRouteDetail.getRoute_num() + "," + busRouteDetail.getStart_location() + "," + busRouteDetail.getEnd_location());

                                }
                                System.out.print(routeNumber + " ");
                                for (int i = 0; i < suitableHalt.size(); i++) {
                                    System.out.print(", " + suitableHalt.get(i).getHaltName() + " ");
                                }
                                System.out.println("");
                                System.out.println("Distance : " + (suitableHalt.get(suitableHalt.size() - 1).getDistance()+parser.getDistance()) + " BusFair : " + (suitableHalt.get(suitableHalt.size() - 1).getBusFair()+parser.getBus_fare()));

                            } else {
                                if (parser.getCalling_Time() < 1) {
                                    //System.out.println("dffff");

                                    ArrayList<String> route_num_tem = parser.getRoute_num();
                                    //route_num_tem.add(routeNumber);
                                    ArrayList<String> list = new ArrayList<>();
                                    for (String route_num : route_num_tem) {
                                        list.add(route_num);
                                    }
                                    list.add(routeNumber);
                                    // System.out.println(route_num_tem.size());

                                    ArrayList<String> start_location_list = new ArrayList<>();
                                    ArrayList<String> start_location = parser.getStart_location();
                                    for (String start_location1 : start_location) {
                                        start_location_list.add(start_location1);
                                    }
                                    BusRoute busRouteDetail = PathChooseController.getBusRouteDetail(routeNumber);
                                    start_location_list.add(busRouteDetail.getEnd_location());

                                    //System.out.println(start_location.size()); 
                                    // System.out.println(busRouteDetail.getEnd_location());
                                    Parser newparser = new Parser(list, start_location_list, parser.getEnd_location(), parser.getBus_fare() + busRouteDetail.getBus_fare(), parser.getBus_fare() + busRouteDetail.getDistance());

                                    newparser.setCalling_Time(parser.getCalling_Time() + 1);
                                    BusRouteChooser busRouteChooser = new BusRouteChooser();
                                    busRouteChooser.getSuitablePath(newparser);
                                }
                            }
                        }
                    } else {//System.out.println("calling time ");

                    }

                }
            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(BusRouteChooser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
