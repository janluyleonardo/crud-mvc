package crud.mvc;

import Controlador.CtrlProducto;
import Modelo.Producto;
import consultas.consultasProducto;
import Vista.frmProducto;

public class CrudMvc {
    
    public static void main(String[] args) {
        // TODO code application logic here
        Producto mod = new Producto();
        consultasProducto modC = new consultasProducto();
        frmProducto frm = new frmProducto();
        CtrlProducto ctrl = new CtrlProducto(mod, modC, frm);
        ctrl.iniciar();
        
        frm.setVisible(true);
    }    
}
