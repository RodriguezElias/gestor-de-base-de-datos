/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class conectar {
    private  Connection CN;
    private static final  String DRIVER = "com.mysql.jdbc.Driver";
    private static final  String USER = "root";
    private static final  String PASSWORD = "";
    private static final  String URL = "jdbc:mysql://localhost:3306/tienda?autoReconnect=true&useSSL=false";

    public conectar() {
        CN = null;
    }
    // este metodo nos retorna la conexion
    public Connection getConnection(){
        
         try{
            Class.forName(DRIVER);
            CN = DriverManager.getConnection(URL, USER, PASSWORD);
            if(CN != null){
            System.out.println("Conexion establecida");
            }
        } catch (ClassNotFoundException | SQLException ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(),"Error al cerrar la conexion con la base de datos", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
        return CN;
    }
    
    //con este metodo nos desconectamos de la base de datos 
    public void close(){
        try {
        CN.close();
        } catch(SQLException ex){
        
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro al cerrar la conexion con la base de datos", JOptionPane.ERROR_MESSAGE);
    }
    
}
} 
