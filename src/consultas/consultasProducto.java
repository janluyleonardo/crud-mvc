package consultas;

import Modelo.Conexion;
import Modelo.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class consultasProducto extends Conexion {
    
    public boolean registrar(Producto pro){
        PreparedStatement ps = null;
        Connection con = getConexion();
        
        String sqlInsert = "INSERT INTO producto (codigo,nombre,precio,cantidad)"
                + "VALUES"
                + "(?,?,?,?)";
        try {
            ps = con.prepareStatement(sqlInsert);
            ps.setString(1, pro.getCodigo());
            ps.setString(2, pro.getNombre());
            ps.setDouble(3, pro.getPrecio());
            ps.setInt(4, pro.getCantidad());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.err.println("error en insercion "+e);
            return false;
        }finally{
            try {
                con.close();
            } catch (Exception e) {
                System.err.println("error en .close() "+e);
            }
        }
    } 

    public boolean modificar(Producto pro){
        PreparedStatement ps = null;
        Connection con = getConexion();
        
        String sqlInsert = "UPDATE producto SET codigo=?,nombre=?,precio=?,cantidad=? WHERE id=?";
        try {
            ps = con.prepareStatement(sqlInsert);
            ps.setString(1, pro.getCodigo());
            ps.setString(2, pro.getNombre());
            ps.setDouble(3, pro.getPrecio());
            ps.setInt(4, pro.getCantidad());
            ps.setInt(5, pro.getId());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.err.println("error en actualizar "+e);
            return false;
        }finally{
            try {
                con.close();
            } catch (Exception e) {
                System.err.println("error en .close() "+e);
            }
        }
    }
    
    public boolean eliminar(Producto pro){
        PreparedStatement ps = null;
        Connection con = getConexion();
        
        String sqlInsert = "DELETE FROM producto WHERE id=?";
        try {
            ps = con.prepareStatement(sqlInsert);
            ps.setInt(1, pro.getId());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.err.println("error en eliminar "+e);
            return false;
        }finally{
            try {
                con.close();
            } catch (Exception e) {
                System.err.println("error en .close() "+e);
            }
        }
    }
    
    public boolean buscar(Producto pro){
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        
        String sqlInsert = "SELECT * FROM producto WHERE codigo=?";
        try {
            ps = con.prepareStatement(sqlInsert);
            ps.setString(1, pro.getCodigo());
            rs = ps.executeQuery();
            
            if(rs.next()){
                pro.setId(Integer.parseInt(rs.getString("id")));
                pro.setCodigo(rs.getString("codigo"));
                pro.setNombre(rs.getString("nombre"));
                pro.setPrecio(Double.parseDouble(rs.getString("precio")));
                pro.setCantidad(Integer.parseInt(rs.getString("cantidad")));
                return true;
            }
            return false;
        } catch (SQLException e) {
            System.err.println("error en buscar "+e);
            return false;
        }finally{
            try {
                con.close();
            } catch (Exception e) {
                System.err.println("error en .close() "+e);
            }
        }
    }
    
    public boolean leer(Producto pro){
        System.out.println("entro en metodo leer");
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        
        String sqlSelect = "SELECT * FROM producto";
        try {
            ps = con.prepareStatement(sqlSelect);
            rs = ps.executeQuery();
            
            if(rs.next()){
                pro.setId(Integer.parseInt(rs.getString("id")));
                pro.setCodigo(rs.getString("codigo"));
                pro.setNombre(rs.getString("nombre"));
                pro.setPrecio(Double.parseDouble(rs.getString("precio")));
                pro.setCantidad(Integer.parseInt(rs.getString("cantidad")));
//                return true;
            }
            System.out.println("termino metodo leer");
            return false;
        } catch (SQLException e) {
            System.out.println("error en leer "+e);
            System.err.println("error en leer "+e);
            return false;
        }finally{
            try {
                con.close();
            } catch (Exception e) {
                System.err.println("error en .close() de leer "+e);
            }
        }
    }
}
