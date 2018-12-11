
package logica;

import clases.conectar;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author pc
 */
public class Clase_Productos {
  private final String SQL_INSERT = "INSERT INTO producto (descripcion_productos,categoria_productos,cantidad_productos,costos_productos,precio_producto,estado_producto) values(?,?,?,?,?,?)"; 
  private final String SQL_SELECT ="SELECT * FROM producto";
  private PreparedStatement PS;
  private final conectar CN;
  private DefaultTableModel DT;
  private ResultSet RS;
  public Clase_Productos(){
      PS = null;
      CN = new conectar();
  }
  
    private DefaultTableModel setTitulos(){
        DT = new DefaultTableModel();
        DT.addColumn("Id");
        DT.addColumn("Descripcion");
        DT.addColumn("Categoria");
        DT.addColumn("Cantidad");
        DT.addColumn("Costo");
        DT.addColumn("Precio");
        DT.addColumn("Estado");
        return DT;
    }
  
  public int updateDatos(String id, String des, String cat, int can, float cos, float pre, String est){
      String SQL = "UPDATE producto SET descripcion_productos='"+des+"',categoria_productos='"+cat+"',cantidad_productos='"+can+"',"
              + "costos_productos='"+cos+"',precio_producto='"+pre+"',estado_producto='"+est+"' WHERE id_productos=" + id;
      int res = 0;
      try{
          PS= CN.getConnection().prepareStatement(SQL);
         
           res=PS.executeUpdate();
          if (res > 0){
         JOptionPane.showMessageDialog(null, "Registro modificado..");                   }
      } catch (SQLException e) {
      System.out.println("Error al modificar los datos en la db: " + e.getMessage());
      } finally{
          PS = null;
          CN.close();
      }
      
      
      
      return res;
  }
  
       public int deletedatos(String id){
            String SQL = "DELETE from producto WHERE id_productos=" + id;
      int res = 0;
      try{
          PS= CN.getConnection().prepareStatement(SQL);
         
           res=PS.executeUpdate();
          if (res > 0){
         JOptionPane.showMessageDialog(null, "Registro eliminado..");                   }
      } catch (SQLException e) {
      System.out.println("Error al eliminar los datos en la db: " + e.getMessage());
      } finally{
          PS = null;
          CN.close();
      }
      return res;
        }
                
                
  public int insertDatos(String des, String cat, int can, float cos, float pre, String est){
      int res = 0;
      try{
          PS= CN.getConnection().prepareStatement(SQL_INSERT);
          PS.setString(1, des);
          PS.setString(2, cat);
          PS.setInt(3, can);
          PS.setFloat(4, cos);
          PS.setFloat(5, pre);
          PS.setString(6, est);
           res=PS.executeUpdate();
          if (res > 0){
         JOptionPane.showMessageDialog(null, "Registro guardado..");                   }
      } catch (SQLException e) {
      System.out.println("Error al guardar los datos en la db: " + e.getMessage());
      } finally{
          PS = null;
          CN.close();
      }
      
      
      
      return res;
  }
  
    public DefaultTableModel getDatos() {
        try {
            setTitulos();
            PS = CN.getConnection().prepareStatement(SQL_SELECT);
            RS = PS.executeQuery();
            Object[] fila = new Object[7];
            while (RS.next()){
                fila[0] = RS.getInt(1);
                fila[1] = RS.getString(2);
                fila[2] = RS.getString(3);
                fila[3] = RS.getInt(4);
                fila[4] = RS.getFloat(5);
                fila[5] = RS.getFloat(6);
                fila[6] = RS.getString(7);
                DT.addRow(fila);
            }
        }
        catch (Exception e) {
            System.out.println("Error al listar datos: " + e.getMessage());
        } finally {
            PS = null;
            RS = null;
            CN.close();
        }
        
        return DT;
    }
    public DefaultTableModel getDato(int crt, String prm) {
       String SQL;
       if (crt == 0){
           SQL = "SELECT * FROM producto WHERE id_productos = " + prm;
       } else {
           SQL = "SELECT * FROM producto WHERE descripcion_productos like '" + prm + "%'";
       }
        try {
            setTitulos();
            PS = CN.getConnection().prepareStatement(SQL);
            RS = PS.executeQuery();
            Object[] fila = new Object[7];
            while (RS.next()){
                fila[0] = RS.getInt(1);
                fila[1] = RS.getString(2);
                fila[2] = RS.getString(3);
                fila[3] = RS.getInt(4);
                fila[4] = RS.getFloat(5);
                fila[5] = RS.getFloat(6);
                fila[6] = RS.getString(7);
                DT.addRow(fila);
            }
        }
        catch (Exception e) {
            System.out.println("Error al listar datos: " + e.getMessage());
        } finally {
            PS = null;
            RS = null;
            CN.close();
        }
        
        return DT;
    }
  
  }
  

