
package com.quantum.modelos;

import java.io.Serializable;

/**
 *
 * @author QUANTUM
 */
public class Iconos implements Serializable{
    
    private String icon;

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Iconos(String icon) {
        this.icon = icon;
    }

    public Iconos() {
    }

    @Override
    public String toString() {
        return "Iconos{" + "icon=" + icon + '}';
    }
    
}
