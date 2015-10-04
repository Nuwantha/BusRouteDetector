/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import db_utilities.DBConnection;
import db_utilities.DBHandler;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import models.BusRoute;
import models.RouteHaltdetail;

/**
 *
 * @author Gimhani
 */
public class PathChooseController {

    public static ArrayList<BusRoute> getDirectBusRoute(String start, String end) throws ClassNotFoundException, SQLException {
        Connection conn = DBConnection.getDBConnection().getConnection();
        String sql = "Select * from BusRoute where startlocation='" + start + "' and endlocation='" + end + "' order by distance";
        ResultSet rst = DBHandler.getData(conn, sql);
        ArrayList<BusRoute> bus_route_list = new ArrayList<>();
        while (rst.next()) {
            BusRoute busRoute = new BusRoute(rst.getString("routeno"), rst.getString("startlocation"), rst.getString("endlocation"), rst.getDouble("busfair"), rst.getDouble("distance"));
            bus_route_list.add(busRoute);
        }
        if (bus_route_list.isEmpty()) {
            return null;
        } else {
            return bus_route_list;
        }
    }

    public static ArrayList<String> getAllStations() throws ClassNotFoundException, SQLException {
        Connection conn = DBConnection.getDBConnection().getConnection();
        String sql = "Select distinct startlocation from BusRoute";
        ResultSet rst = DBHandler.getData(conn, sql);
        ArrayList<String> station_list = new ArrayList<>();
        while (rst.next()) {
            station_list.add(rst.getString("startlocation"));
        }
        sql = "Select distinct endlocation from BusRoute where endlocation not in (Select distinct startlocation from BusRoute) ";
        rst = DBHandler.getData(conn, sql);

        while (rst.next()) {
            station_list.add(rst.getString("endlocation"));
        }
        return station_list;
    }

    public static ArrayList<String> getAllEndStations(String likename) throws ClassNotFoundException, SQLException {
        Connection conn = DBConnection.getDBConnection().getConnection();
        String sql = "Select distinct startlocation from BusRoute where startlocation like '%" + likename + "%'";
        ResultSet rst = DBHandler.getData(conn, sql);
        ArrayList<String> end_station_list = new ArrayList<>();
        while (rst.next()) {
            end_station_list.add(rst.getString("startlocation"));
        }
        sql = "Select distinct endlocation from BusRoute where endlocation like '" + likename + "%' and endlocation not in (Select distinct startlocation from BusRoute) ";
        rst = DBHandler.getData(conn, sql);

        while (rst.next()) {
            end_station_list.add(rst.getString("endlocation"));
        }
        return end_station_list;
    }

    public static ArrayList<String> getAllIntermediateStations() throws ClassNotFoundException, SQLException {
        Connection conn = DBConnection.getDBConnection().getConnection();
        String sql = "Select name from halt";
        ResultSet rst = DBHandler.getData(conn, sql);
        ArrayList<String> halt_list = new ArrayList<>();
        while (rst.next()) {
            halt_list.add(rst.getString("name"));
        }
        return halt_list;
    }

    public static ArrayList<String> getAllIntermediateStations(String likename) throws ClassNotFoundException, SQLException {
        Connection conn = DBConnection.getDBConnection().getConnection();
        String sql = "Select name from halt where name like '" + likename + "%'";
        ResultSet rst = DBHandler.getData(conn, sql);
        ArrayList<String> halt_list = new ArrayList<>();
        while (rst.next()) {
            halt_list.add(rst.getString("name"));
        }
        return halt_list;
    }

    public static ArrayList<String> getRouteNumbers(String location) throws ClassNotFoundException, SQLException {
        Connection conn = DBConnection.getDBConnection().getConnection();
        String sql = "select distinct routeno from busroute where startLocation='" + location + "'";
        ResultSet rst = DBHandler.getData(conn, sql);
        ArrayList<String> route_list = new ArrayList<>();
        while (rst.next()) {
            route_list.add(rst.getString("routeNo"));
        }
        if (route_list.isEmpty()) {
            return null;
        } else {
            return route_list;
        }
    }
    
    public static ArrayList<RouteHaltdetail> getSuitableHalt(String routeNo,String endLocation) throws ClassNotFoundException, SQLException {
        Connection conn = DBConnection.getDBConnection().getConnection();
        String sql = "select * from halt natural join routehaltdetail where routeno='"+routeNo+"' and distance <= (select distance from halt natural join routehaltdetail where routeno='"+routeNo+"' and name='"+endLocation+"')";
        ResultSet rst = DBHandler.getData(conn, sql);
        ArrayList<RouteHaltdetail> halt_list = new ArrayList<>();
        while (rst.next()) {
            RouteHaltdetail routeHaltdetail = new RouteHaltdetail(rst.getString("routeNo"),rst.getInt("HaltNo"),rst.getString("Name"), rst.getDouble("distance"),rst.getDouble("busFair"));
            halt_list.add(routeHaltdetail);
        }
        if (halt_list.isEmpty()) {
            return null;
        } else {
            return halt_list;
        }
    }
   
    public static String getEndLocationOfTheRoute(String routeNo) throws ClassNotFoundException, SQLException {
        Connection conn = DBConnection.getDBConnection().getConnection();
        String sql = "select endLocation from busRoute where routeNo='"+routeNo+"'";
        ResultSet rst = DBHandler.getData(conn, sql);
        if(rst.next()) {
            return rst.getString("endLocation");
        }else{
            return null;
        }
        
        
        
    }
   
    public static BusRoute getBusRouteDetail(String routeNo) throws ClassNotFoundException, SQLException {
        Connection conn = DBConnection.getDBConnection().getConnection();
        String sql = "Select * from BusRoute where routeNo = '"+routeNo+"'";
        ResultSet rst = DBHandler.getData(conn, sql);
        if (rst.next()) {
            BusRoute busRoute = new BusRoute(rst.getString("routeno"), rst.getString("startlocation"), rst.getString("endlocation"), rst.getDouble("busfair"), rst.getDouble("distance"));
            return busRoute;
        }else{
            return null;
        }
    }
    
      
      public static ArrayList<String> getRouteOfHalt(String haltName) throws ClassNotFoundException, SQLException {
        Connection conn = DBConnection.getDBConnection().getConnection();
        String sql = "select routeNo from halt natural join routehaltdetail where name='"+haltName+"' ";
        ResultSet rst = DBHandler.getData(conn, sql);
        ArrayList<String> routeList = new ArrayList<>();
        while (rst.next()) {
            routeList.add(rst.getString("routeNo"));
        }
        if (routeList.isEmpty()) {
            return null;
        } else {
            return routeList;
        }
    } 

    
     public static RouteHaltdetail getRouteDetailOfHalt(String haltName,String routeNo) throws ClassNotFoundException, SQLException {
        Connection conn = DBConnection.getDBConnection().getConnection();
        String sql = "select * from halt natural join routehaltdetail where name='"+haltName+"' and routeNo ='"+routeNo+"' ";
        ResultSet rst = DBHandler.getData(conn, sql);
        
        if (rst.next()) {
            return new RouteHaltdetail(routeNo, rst.getInt("haltNo"), haltName,rst.getDouble("distance"), rst.getDouble("busFair"));
            
        } else {
            return null;
        }
    }  
      
}
