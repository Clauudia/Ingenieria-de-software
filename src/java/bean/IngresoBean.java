/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import dao.IngresoDAO;
import model.Ingreso;
/**
 *
 * @author claudia
 */
public class IngresoBean {
    
    private Ingreso user = new Ingreso(); 
    
    public Ingreso getIngreso(){
        return user;
    }

    public void setIngreso(Ingreso user){
        this.user = user;
    }
    
    public String verificaDatos() throws Exception {
        IngresoDAO ingresoDAO = new IngresoDAO();
        Ingreso us;
        String resultado;
        
        try {
            us = ingresoDAO.verificaDatos(this.user);
            if(us != null) {
                
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
                        .put("usuario", us);
                resultado = "éxito";
            } else {
                resultado = "error";
            }
        } catch (Exception e) {
            throw e;
        }
        
        return resultado;
        
    }
    
    public String cierraSesion() {
		FacesContext.getCurrentInstance().getExternalContext()
				.invalidateSession();
		return "index";
	}
}
