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

}
