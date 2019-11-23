/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idat.edu.pe.sigedu.modelo.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrador
 */
public class JDBCUtil {
    
    private final String usuarioBD = "root";
    
    private final String passwordBD = "12345678";
    
    private final String urlConeccion = "jdbc:mysql://localhost:3306/sigedu_db?zeroDateTimeBehavior=convertToNull";
    
    private final String driverClass = "com.mysql.jdbc.Driver";
    
    private Connection conneccion;
    
    private PreparedStatement sentencia;
    
    private ResultSet resultados;

    public JDBCUtil() {
        
        try {
            //1.-Cargo la cla Driver
            Class.forName(driverClass);
            //2.-Obtener connecion
            conneccion = DriverManager.getConnection(urlConeccion, usuarioBD, passwordBD);
            if (conneccion != null)
                System.out.println("Coneccion Exitosa");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(JDBCUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Connection getConneccion() {
        return conneccion;
    }
    
    public PreparedStatement obtenerPreparedStatement(String sql)
    {
    
        try {
            sentencia = conneccion.prepareStatement(sql);   
        } catch (SQLException ex) {
            Logger.getLogger(JDBCUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sentencia;
    }
    
    public ResultSet ejecutarConsulta(PreparedStatement statementSQL)
    {
        sentencia = statementSQL;
        try {
            resultados = sentencia.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(JDBCUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return resultados;
    }
    
    public void ejecutarActualizacion(PreparedStatement statementSQL)
    {
        sentencia = statementSQL;
        try {
            sentencia.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(JDBCUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void cerrarObjetos()
    {
        try {
            if (resultados != null)
                resultados.close();
            if (sentencia != null)
                sentencia.close();
            if(conneccion != null)
                conneccion.close();
        } catch (SQLException ex) {
            Logger.getLogger(JDBCUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
}
