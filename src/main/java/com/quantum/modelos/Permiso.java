
package com.quantum.modelos;

import java.io.Serializable;


public class Permiso implements Serializable{
   
    private String codMenu;
    
    private boolean check;

    public String getCodMenu() {
        return codMenu;
    }

    public void setCodMenu(String codMenu) {
        this.codMenu = codMenu;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    @Override
    public String toString() {
        return "Permiso{" + "codMenu=" + codMenu + ", check=" + check + '}';
    }

    public Permiso(String codMenu, boolean check) {
        this.codMenu = codMenu;
        this.check = check;
    }

    public Permiso(String codMenu) {
        this.codMenu = codMenu;
    }
    
    
}
