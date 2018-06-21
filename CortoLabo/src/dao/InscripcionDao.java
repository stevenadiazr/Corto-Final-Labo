/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexion.Conexion;
import interfaces.Metodos;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Inscripciones;

/**
 *
 * @author Steven Diaz
 */
public class InscripcionDao implements Metodos<Inscripciones>{
    private static final String SQL_INSERT = "INSERT INTO movie (numeroAFP, nombre, apellido, profesion, edad, estado) VALUES(?,?,?,?,?,?)";
    private static final String SQL_UPDATE = "UPDATE movie SET estado = ? WHERE nombre = ?";
    private static final String SQL_DELETE = "DELETE FROM movie WHERE nombre = ?";
    private static final String SQL_READ = "SELECT * FROM movie WHERE nombre = ?";
    private static final String SQL_READALL = "SELECT * FROM movie";
    private static final Conexion con = Conexion.conectar();

    @Override
    public boolean create(Inscripciones g) {
        PreparedStatement ps;
        try {
            ps = con.getCnx().prepareStatement(SQL_INSERT);
            ps.setInt(1, g.getNumeroAFP());
            ps.setString(2, g.getNombre());
            ps.setString(3, g.getApellidos());
            ps.setString(5, g.getProfesion());
            ps.setInt(6, g.getEdad());
            ps.setBoolean(7, true);
            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(InscripcionDao.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            con.cerrarConexion();
        }
        return false;    }

    @Override
    public boolean delete(Object key) {
        PreparedStatement ps;
        try {
            ps = con.getCnx().prepareStatement(SQL_DELETE);
            ps.setString(1, key.toString());
            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(InscripcionDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.cerrarConexion();
        }
        return false;
    }

    @Override
    public boolean update(Inscripciones c) {
        PreparedStatement ps;
        try {
            System.out.println(c.getNombre());
            ps = con.getCnx().prepareStatement(SQL_UPDATE);
            ps.setBoolean(1, c.getEstado());
            ps.setString(2, c.getNombre());
            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(InscripcionDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.cerrarConexion();
        }
        return false;
    }

    @Override
    public Inscripciones read(Object key) {
        Inscripciones p = null;
        PreparedStatement ps;
        ResultSet rs;

        try {
            ps = con.getCnx().prepareStatement(SQL_READ);
            ps.setString(1, key.toString());
            rs = ps.executeQuery();

            while (rs.next()) {
                //nombre, apellidos, numeroAFP, profesion, edad, estado
                p = new Inscripciones(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getInt(5), rs.getBoolean(6));//constructor
            }
            rs.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(InscripcionDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.cerrarConexion();
        }
        return p;
    }

    @Override
    public ArrayList<Inscripciones> readAll() {
        ArrayList<Inscripciones> all = new ArrayList();
        Statement s;
        ResultSet rs;

        try {
            s = con.getCnx().prepareStatement(SQL_READALL);
            rs = s.executeQuery(SQL_READALL);

            while (rs.next()) {
                //idMovie, nombre, director, pais, clasificacion, anio, en_proyeccion
                all.add(new Inscripciones(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getInt(5), rs.getBoolean(6)));
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(InscripcionDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return all;
    }

    
    
}
