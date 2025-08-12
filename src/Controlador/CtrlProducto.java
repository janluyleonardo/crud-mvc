package Controlador;

import Modelo.Producto;
import consultas.consultasProducto;
import Vista.frmProducto;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class CtrlProducto implements ActionListener {

    private Producto mod;
    private consultasProducto modC;
    private frmProducto frm;

    public CtrlProducto(Producto mod, consultasProducto modC, frmProducto frm) {
        this.mod = mod;
        this.modC = modC;
        this.frm = frm;
        this.frm.btnGuardar.addActionListener(this);
        this.frm.btnEliminar.addActionListener(this);
        this.frm.btnModificar.addActionListener(this);
        this.frm.btnLimpiar.addActionListener(this);
        this.frm.btnBuscar.addActionListener(this);
        
        DefaultTableModel modTabProducto;
        modTabProducto = new DefaultTableModel();
        modTabProducto.addColumn("Código");
        modTabProducto.addColumn("Nombre");
        modTabProducto.addColumn("Precio");
        modTabProducto.addColumn("Cantidad");
        frm.jtProducto.setModel(modTabProducto);
        modC.leer(mod);
    }

    public void iniciar() {
        frm.setTitle("Productos");
        frm.setLocationRelativeTo(null);
        frm.txtId.setVisible(false);
    }

    @Override
//      captura el action actionPerformed en la variable e
    public void actionPerformed(ActionEvent e) {
//        vaalida si la variable pertenece al btnGuardar del formulario
        if (e.getSource() == frm.btnGuardar) {
//            setea las variables en el modelo de datos
            mod.setCodigo(frm.txtCodigo.getText());
            mod.setNombre(frm.txtNombre.getText());
            mod.setPrecio(Double.parseDouble(frm.txtPrecio.getText()));
            mod.setCantidad(Integer.parseInt(frm.txtCantidad.getText()));
//            valida si el modelo de consultas regresa true
            if(modC.registrar(mod)){
                JOptionPane.showMessageDialog(null, "Registro agregado en la DB");
                limpiar();
                modC.leer(mod);
            }else{
                JOptionPane.showMessageDialog(null, "Error al agregar registro en la DB");                
                limpiar();
                modC.leer(mod);
            }
        }
        
        if (e.getSource() == frm.btnModificar) {
            mod.setId(Integer.parseInt(frm.txtId.getText()));
            mod.setCodigo(frm.txtCodigo.getText());
            mod.setNombre(frm.txtNombre.getText());
            mod.setPrecio(Double.parseDouble(frm.txtPrecio.getText()));
            mod.setCantidad(Integer.parseInt(frm.txtCantidad.getText()));
            
            if(modC.modificar(mod)){
                JOptionPane.showMessageDialog(null, "Registro modificado en la DB");
                limpiar();
            }else{
                JOptionPane.showMessageDialog(null, "Error al modificar registro en la DB");                
                limpiar();
            }
        }
        
        if (e.getSource() == frm.btnEliminar) {
            mod.setId(Integer.parseInt(frm.txtId.getText()));
            
            if(modC.eliminar(mod)){
                JOptionPane.showMessageDialog(null, "Registro eliminado en la DB");
                limpiar();
            }else{
                JOptionPane.showMessageDialog(null, "Error al eliminar registro en la DB");                
                limpiar();
            }
        }
        
        if (e.getSource() == frm.btnBuscar) {
            mod.setCodigo(frm.txtCodigo.getText());
            
            if(modC.buscar(mod)){
                frm.txtId.setText(String.valueOf(mod.getId()));
                frm.txtCodigo.setText(mod.getCodigo());
                frm.txtNombre.setText(mod.getNombre());
                frm.txtPrecio.setText(String.valueOf(mod.getPrecio()));
                frm.txtCantidad.setText(String.valueOf(mod.getCantidad()));
            }else{
                System.err.println("Error al buscar registro en la DB");                
                limpiar();
            }
        }
        
        if (e.getSource() == frm.btnLimpiar) {
            limpiar();
        }
    }
    
    public void limpiar(){
        frm.txtCantidad.setText("");
        frm.txtCodigo.setText("");
        frm.txtId.setText("");
        frm.txtNombre.setText("");
        frm.txtPrecio.setText("");
    }
}
