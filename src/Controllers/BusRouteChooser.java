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

/**
 *
 * @author DinsuG
 */
public class BusRouteChooser {
    public void getSuitablePath(String start,String end){
        try {
            // TODO code application logic here
            ArrayList<BusRoute> directBusRoute = PathChooseController.getDirectBusRoute(start, end);
            if (directBusRoute != null) {
                for (BusRoute directBusRoute1 : directBusRoute) {
                    System.out.println(directBusRoute1.getRoute_num() + "," + directBusRoute1.getStart_location() + "," + directBusRoute1.getEnd_location() + "," + directBusRoute1.getBus_fare() + "," + directBusRoute1.getDistance());
                }
            }else{
                System.out.println("no result");
            }
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(BusRouteChooser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
