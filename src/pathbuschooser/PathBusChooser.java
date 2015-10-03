/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pathbuschooser;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.BusRoute;

/**
 *
 * @author DinsuG
 */
public class PathBusChooser {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // TODO code application logic here
            ArrayList<BusRoute> directBusRoute = PathChooseController.getDirectBusRoute("Galle", "Baddegama");
            for (BusRoute directBusRoute1 : directBusRoute) {
                System.out.println(directBusRoute1.getRoute_num()+","+directBusRoute1.getStart_location()+","+directBusRoute1.getEnd_location()+","+directBusRoute1.getBus_fare()+","+directBusRoute1.getDistance());
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PathBusChooser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PathBusChooser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}