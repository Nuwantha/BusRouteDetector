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
            
            //arry list ekak gan nathuwa limit karala eka hodama route eka ganna
            ArrayList<BusRoute> directBusRoute = PathChooseController.getDirectBusRoute(parser.getStart_location().get(parser.getStart_location().size() - 1), parser.getEnd_location());
            if (directBusRoute != null) {
                System.out.println("directed way");
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
                    System.out.println("end direted");
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

                    //check for an intermediate halt stating from an end station
                    ArrayList<String> routeNumbers2 = PathChooseController.getRouteNumbersEnd(parser.getStart_location().get(parser.getStart_location().size() - 1));
                   
                    if (routeNumbers2 != null) {
                         //System.out.println(routeNumbers2.size());
                        for (String routeNumbers21 : routeNumbers2) {
                            ArrayList<RouteHaltdetail> suitableHaltEnd = PathChooseController.getSuitableHaltEnd(routeNumbers21, parser.getEnd_location());
                            System.out.println("check for an intermediate halt stating from an end station "+routeNumbers21 );
                            if (suitableHaltEnd != null) {
                                textArea.append("Hai ");

                                ArrayList<String> route_num = parser.getRoute_num();
                                for (String route_num1 : route_num) {
                                    BusRoute busRouteDetail = PathChooseController.getBusRouteDetail(route_num1);
                                    return_string += (busRouteDetail.getRoute_num() + "," + busRouteDetail.getStart_location() + "," + busRouteDetail.getEnd_location() + "\n");

                                }

                                return_string += (routeNumbers21 + " ");
                                for (int i = suitableHaltEnd.size() - 1; i >= 0; i--) {
                                    return_string += (", " + suitableHaltEnd.get(i).getHaltName() + " ");
                                }
                                BusRoute busRouteDetail = PathChooseController.getBusRouteDetail(routeNumbers21);
                                return_string += ("\n");
                                return_string += ("Distance : " + ((busRouteDetail.getDistance() - suitableHaltEnd.get(0).getDistance()) + parser.getDistance()) + "\n BusFair : " + (busRouteDetail.getBus_fare() - (suitableHaltEnd.get(suitableHaltEnd.size() - 1).getBusFair()) + parser.getBus_fare()) + "\n");
                                textArea.append(return_string);
                            } else {

                                if (parser.getCalling_Time() < 2) {
                                    //int tem = 1;
                                    
                                    //create a new list and add all elements of passer's route number arraylist to it
                                    ArrayList<String> route_num_tem = parser.getRoute_num();
                                    ArrayList<String> list = new ArrayList<>();
                                    for (String route_num : route_num_tem) {
                                        list.add(route_num);
                                    }
                                    
                                    //create a new list and add all elements of passer's start location arraylist to it
                                    ArrayList<String> start_location_list = new ArrayList<>();
                                    ArrayList<String> start_location = parser.getStart_location();
                                    for (String start_location1 : start_location) {
                                        start_location_list.add(start_location1);
                                    }
                                    BusRoute busRouteDetail = PathChooseController.getBusRouteDetail(routeNumbers21);

                                    if (!list.contains(routeNumbers21)) {
                                        list.add(routeNumbers21); //prevent repeatition of a used route
                                        start_location_list.add(busRouteDetail.getStart_location());
                                        
                                        Passer newparser = new Passer(list, start_location_list, parser.getEnd_location(), parser.getBus_fare() + busRouteDetail.getBus_fare(), parser.getBus_fare() + busRouteDetail.getDistance());
                                        newparser.setCalling_Time(parser.getCalling_Time() +1);
                                        getSuitablePath(newparser);
                                    } else {
                                       // tem += 20;
                                    }
                                    
                                    //if (!start_location_list.contains(busRouteDetail.getStart_location())) {
                                        
                                    //}
                                    //start_location_list.add();

                                    //Passer newparser = new Passer(list, start_location_list, parser.getEnd_location(), parser.getBus_fare() + busRouteDetail.getBus_fare(), parser.getBus_fare() + busRouteDetail.getDistance());

                                    //newparser.setCalling_Time(parser.getCalling_Time() + tem);
                                    //getSuitablePath(newparser);
                                }

                            }

                        }

                    }

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
                                if (parser.getCalling_Time() < 2) {
                                    //int tem = 1;

                                    ArrayList<String> route_num_tem = parser.getRoute_num();
                                    ArrayList<String> list = new ArrayList<>();

                                    ArrayList<String> start_location_list = new ArrayList<>();
                                    ArrayList<String> start_location = parser.getStart_location();
                                    for (String start_location1 : start_location) {
                                        start_location_list.add(start_location1);
                                    }

                                    for (String route_num : route_num_tem) {
                                        list.add(route_num);
                                    }
                                    BusRoute busRouteDetail = PathChooseController.getBusRouteDetail(routeNumber);

                                    if (list.contains(routeNumber)) {
                                        list.add(routeNumber);                                        
                                        start_location_list.add(busRouteDetail.getEnd_location());
                                        Passer newparser = new Passer(list, start_location_list, parser.getEnd_location(), parser.getBus_fare() + busRouteDetail.getBus_fare(), parser.getBus_fare() + busRouteDetail.getDistance());

                                    newparser.setCalling_Time(parser.getCalling_Time() + 1);
                                    getSuitablePath(newparser);
                                    } else {
                                       // tem += 10;
                                    }

                                   // if(list.contains(busRouteDetail.getEnd_location())){
                                    //}
                                   // Passer newparser = new Passer(list, start_location_list, parser.getEnd_location(), parser.getBus_fare() + busRouteDetail.getBus_fare(), parser.getBus_fare() + busRouteDetail.getDistance());

                                   // newparser.setCalling_Time(parser.getCalling_Time() + tem);
                                   // getSuitablePath(newparser);
                                }
                            }
                        }
                    }
                    //check start from an intermediate station
                    if (routeNumbers == null && routeNumbers2 == null) {
                        if (parser.getCalling_Time() < 1) {

                            //getting the routes with this halt in middle
                            ArrayList<String> routeOfHalt = PathChooseController.getRouteOfHalt(parser.getStart_location().get(parser.getStart_location().size() - 1));
                            
                            if (routeOfHalt != null) {
                                for (String routeOfHalt1 : routeOfHalt) {
                                    System.out.println(routeOfHalt1);
                                    int tem = 1;
                                    //get details about the selected route
                                    BusRoute busRouteDetail = PathChooseController.getBusRouteDetail(routeOfHalt1);
                                    
                                    //adding data of old passer to a new passer
                                    ArrayList<String> start_location = parser.getStart_location();
                                    ArrayList<String> route_num = parser.getRoute_num();

                                    ArrayList<String> new_route_num = new ArrayList<>();
                                    for (String route_num1 : route_num) {
                                        new_route_num.add(route_num1);
                                        System.out.println("loop 1");
                                    }

                                    new_route_num.add(routeOfHalt1);

                                    ArrayList<String> new_start_location = new ArrayList<>();
                                    for (String start_location1 : start_location) {
                                        new_start_location.add(start_location1);
                                    }

                                    new_start_location.add(busRouteDetail.getStart_location());
                                    RouteHaltdetail routeDetailOfHalt = PathChooseController.getRouteDetailOfHalt(parser.getStart_location().get(parser.getStart_location().size() - 1), routeOfHalt1);
                                    
                                    //creating the passer for the start of route of given starting halt
                                    Passer newparser = new Passer(new_route_num, new_start_location, parser.getEnd_location(), routeDetailOfHalt.getBusFair(), routeDetailOfHalt.getDistance());
                                    newparser.setCalling_Time(parser.getCalling_Time()+1);
                                    getSuitablePath(newparser);

                                    //to start arraylist add existing start(halt) and the end station of route
                                    ArrayList<String> new_start_location_byend = new ArrayList<>();
                                    for (String start_location1 : start_location) {
                                        new_start_location_byend.add(start_location1);
                                    }
                                    new_start_location_byend.add(busRouteDetail.getEnd_location());
                                    
                                    //creating th passer for the destination station of route
                                    Passer newparser2 = new Passer(new_route_num, new_start_location_byend, parser.getEnd_location(), busRouteDetail.getBus_fare() - routeDetailOfHalt.getBusFair(), busRouteDetail.getDistance() - routeDetailOfHalt.getDistance());
                                    newparser2.setCalling_Time(parser.getCalling_Time()+1);
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
