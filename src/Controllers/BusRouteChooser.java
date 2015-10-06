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
import javax.swing.JTextArea;
import models.BusRoute;
import models.Passer;
import models.RouteHaltdetail;

/**
 *
 * @author DinsuG
 */
public class BusRouteChooser {

    public JTextArea textArea = null;

    public void getSuitablePath(Passer parser) {
        String return_string = "===================================================\n"; //the string to be returned by this method

        try {

            //check whether both station are  same
            if (parser.getEnd_location().equals(parser.getStart_location().get(parser.getStart_location().size() - 1))) {
                return_string += "You have entered both start and destination as same name!";
                textArea.append(return_string);
                return;
            }

            //chech whether you want to go from stat location to end station
            ArrayList<BusRoute> directBusRoute = PathChooseController.getDirectBusRoute(parser.getStart_location().get(parser.getStart_location().size() - 1), parser.getEnd_location());
            if (directBusRoute != null) {
                ArrayList<String> route_num = parser.getRoute_num();
                for (String route_num1 : route_num) {
                    BusRoute busRouteDetail = PathChooseController.getBusRouteDetail(route_num1);
                    return_string += (busRouteDetail.getRoute_num() + "," + busRouteDetail.getStart_location() + "," + busRouteDetail.getEnd_location() + "\n");

                }
                for (BusRoute directBusRoute1 : directBusRoute) {
                    return_string += (directBusRoute1.getRoute_num() + "," + directBusRoute1.getStart_location() + "," + directBusRoute1.getEnd_location() + "\n");
                    return_string += ("Distance : " + (parser.getDistance() + directBusRoute1.getDistance()) + "\n");
                    return_string += ("BusFair : " + (parser.getBus_fare() + directBusRoute1.getBus_fare()) + "\n");
                }

                textArea.append(return_string);

            } else {

                //chech whether you want to go from end location to start station
                directBusRoute = PathChooseController.getDirectBusRoute(parser.getEnd_location(), parser.getStart_location().get(parser.getStart_location().size() - 1));
                if (directBusRoute != null) {
                    ArrayList<String> route_num = parser.getRoute_num();
                    for (String route_num1 : route_num) {
                        BusRoute busRouteDetail = PathChooseController.getBusRouteDetail(route_num1);
                        return_string += (busRouteDetail.getRoute_num() + "," + busRouteDetail.getStart_location() + "," + busRouteDetail.getEnd_location() + "\n");

                    }
                    for (BusRoute directBusRoute1 : directBusRoute) {
                        return_string += (directBusRoute1.getRoute_num() + "," + directBusRoute1.getStart_location() + "," + directBusRoute1.getEnd_location() + "\n");
                        return_string += ("Distance : " + (parser.getDistance() + directBusRoute1.getDistance()) + "\n");
                        return_string += ("BusFair : " + (parser.getBus_fare() + directBusRoute1.getBus_fare()) + "\n");
                    }

                    textArea.append(return_string);

                    //for (BusRoute directBusRoute1 : directBusRoute) {
                      //  return_string += (directBusRoute1.getRoute_num() + "," + directBusRoute1.getEnd_location() + "," + directBusRoute1.getStart_location() + "," + directBusRoute1.getBus_fare() + "," + directBusRoute1.getDistance() + "\n");
                    //}
                } else {
                    //check for an intermediate halt stating from a start station
                    ArrayList<String> routeNumbers = PathChooseController.getRouteNumbers(parser.getStart_location().get(parser.getStart_location().size() - 1));
                    
                    //ArrayList<String> routeNumbers2 = PathChooseController.getRouteNumbers(parser.getEnd_location());
                    if (routeNumbers != null) {
                                        
                        for (String routeNumber : routeNumbers) {
                            //return_string+=("route nuimber" +routeNumber +"  "+parser.getStart_location().get(parser.getStart_location().size() - 1));
                           
                            ArrayList<RouteHaltdetail> suitableHalt = PathChooseController.getSuitableHalt(routeNumber, parser.getEnd_location());
                            if (suitableHalt != null) {
                                textArea.append("Hai ");
                                
                                ArrayList<String> route_num = parser.getRoute_num();
                                for (String route_num1 : route_num) {
                                    BusRoute busRouteDetail = PathChooseController.getBusRouteDetail(route_num1);
                                    return_string += (busRouteDetail.getRoute_num() + "," + busRouteDetail.getStart_location() + "," + busRouteDetail.getEnd_location() + "\n");

                                }
                                return_string += (routeNumber + " ");
                                for (int i = 0; i < suitableHalt.size(); i++) {
                                    return_string += (", " + suitableHalt.get(i).getHaltName() + " ");
                                }
                                return_string += ("\n");
                                return_string += ("Distance : " + (suitableHalt.get(suitableHalt.size() - 1).getDistance() + parser.getDistance()) + "\n BusFair : " + (suitableHalt.get(suitableHalt.size() - 1).getBusFair() + parser.getBus_fare()) + "\n");
                                textArea.append(return_string);
                            } else {
                                if (parser.getCalling_Time() < 1) {

                                    ArrayList<String> route_num_tem = parser.getRoute_num();
                                    ArrayList<String> list = new ArrayList<>();
                                    for (String route_num : route_num_tem) {
                                        list.add(route_num);
                                    }
                                    list.add(routeNumber);
                                    ArrayList<String> start_location_list = new ArrayList<>();
                                    ArrayList<String> start_location = parser.getStart_location();
                                    for (String start_location1 : start_location) {
                                        start_location_list.add(start_location1);
                                    }
                                    BusRoute busRouteDetail = PathChooseController.getBusRouteDetail(routeNumber);
                                    start_location_list.add(busRouteDetail.getEnd_location());

                                    Passer newparser = new Passer(list, start_location_list, parser.getEnd_location(), parser.getBus_fare() + busRouteDetail.getBus_fare(), parser.getBus_fare() + busRouteDetail.getDistance());

                                    newparser.setCalling_Time(parser.getCalling_Time() + 1);
                                    getSuitablePath(newparser);
                                }
                            }
                        }
                    } else {
                        if (parser.getCalling_Time() < 3) {

                            ArrayList<String> routeOfHalt = PathChooseController.getRouteOfHalt(parser.getStart_location().get(parser.getStart_location().size() - 1));
                            if (routeOfHalt != null) {
                                for (String routeOfHalt1 : routeOfHalt) {
                                    BusRoute busRouteDetail = PathChooseController.getBusRouteDetail(routeOfHalt1);
                                    ArrayList<String> start_location = parser.getStart_location();
                                    ArrayList<String> route_num = parser.getRoute_num();

                                    ArrayList<String> new_route_num = new ArrayList<>();
                                    for (String route_num1 : route_num) {
                                        new_route_num.add(route_num1);
                                    }

                                    new_route_num.add(routeOfHalt1);

                                    ArrayList<String> new_start_location = new ArrayList<>();
                                    for (String start_location1 : start_location) {
                                        new_start_location.add(start_location1);
                                    }

                                    new_start_location.add(busRouteDetail.getStart_location());
                                    RouteHaltdetail routeDetailOfHalt = PathChooseController.getRouteDetailOfHalt(parser.getStart_location().get(parser.getStart_location().size() - 1), routeOfHalt1);
                                    Passer newparser = new Passer(new_route_num, new_start_location, parser.getEnd_location(), routeDetailOfHalt.getBusFair(), routeDetailOfHalt.getDistance());
                                    getSuitablePath(newparser);

                                    ArrayList<String> new_start_location_byend = new ArrayList<>();
                                    for (String start_location1 : start_location) {
                                        new_start_location_byend.add(start_location1);
                                    }

                                    new_start_location_byend.add(busRouteDetail.getEnd_location());
                                    Passer newparser2 = new Passer(new_route_num, new_start_location_byend, parser.getEnd_location(), busRouteDetail.getBus_fare() - routeDetailOfHalt.getBusFair(), busRouteDetail.getDistance() - routeDetailOfHalt.getDistance());
                                    getSuitablePath(newparser2);
                                }

                            }
                        }

                    }

                }
                if (parser != null) {
                   // textArea.setText(return_string);
                }
            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(BusRouteChooser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
