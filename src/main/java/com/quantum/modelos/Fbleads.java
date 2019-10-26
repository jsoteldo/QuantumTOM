
package com.quantum.modelos;

import java.io.Serializable;

/**
 *
 * @author QUANTUM
 */
public class Fbleads implements Serializable{
    
    private String id;
    private String created_time;
    private String ad_id;
    private String ad_name;
    private String adset_id;
    private String adset_name;
    private String campaign_id;
    private String campaign_name;
    private String form_id;
    private String form_name;
    private String is_organic;
    private String plataform;
    private String email;
    private String phone_number;
    private String full_name;
    private String sup_encargado;
    
    public Fbleads() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreated_time() {
        return created_time;
    }

    public void setCreated_time(String created_time) {
        this.created_time = created_time;
    }

    public String getAd_id() {
        return ad_id;
    }

    public void setAd_id(String ad_id) {
        this.ad_id = ad_id;
    }

    public String getAd_name() {
        return ad_name;
    }

    public void setAd_name(String ad_name) {
        this.ad_name = ad_name;
    }

    public String getAdset_id() {
        return adset_id;
    }

    public void setAdset_id(String adset_id) {
        this.adset_id = adset_id;
    }

    public String getAdset_name() {
        return adset_name;
    }

    public void setAdset_name(String adset_name) {
        this.adset_name = adset_name;
    }

    public String getCampaign_id() {
        return campaign_id;
    }

    public void setCampaign_id(String campaign_id) {
        this.campaign_id = campaign_id;
    }

    public String getCampaign_name() {
        return campaign_name;
    }

    public void setCampaign_name(String campaign_name) {
        this.campaign_name = campaign_name;
    }

    public String getForm_id() {
        return form_id;
    }

    public void setForm_id(String form_id) {
        this.form_id = form_id;
    }

    public String getForm_name() {
        return form_name;
    }

    public void setForm_name(String form_name) {
        this.form_name = form_name;
    }

    public String getIs_organic() {
        return is_organic;
    }

    public void setIs_organic(String is_organic) {
        this.is_organic = is_organic;
    }

    public String getPlataform() {
        return plataform;
    }

    public void setPlataform(String plataform) {
        this.plataform = plataform;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getSup_encargado() {
        return sup_encargado;
    }

    public void setSup_encargado(String sup_encargado) {
        this.sup_encargado = sup_encargado;
    }

    public Fbleads(String id, String created_time, String ad_id, String ad_name, String adset_id, String adset_name, String campaign_id, String campaign_name, String form_id, String form_name, String is_organic, String plataform, String email, String phone_number, String full_name, String sup_encargado) {
        this.id = id;
        this.created_time = created_time;
        this.ad_id = ad_id;
        this.ad_name = ad_name;
        this.adset_id = adset_id;
        this.adset_name = adset_name;
        this.campaign_id = campaign_id;
        this.campaign_name = campaign_name;
        this.form_id = form_id;
        this.form_name = form_name;
        this.is_organic = is_organic;
        this.plataform = plataform;
        this.email = email;
        this.phone_number = phone_number;
        this.full_name = full_name;
        this.sup_encargado = sup_encargado;
    }

    @Override
    public String toString() {
        return "Fbleads{" + "id=" + id + ", created_time=" + created_time + ", ad_id=" + ad_id + ", ad_name=" + ad_name + ", adset_id=" + adset_id + ", adset_name=" + adset_name + ", campaign_id=" + campaign_id + ", campaign_name=" + campaign_name + ", form_id=" + form_id + ", form_name=" + form_name + ", is_organic=" + is_organic + ", plataform=" + plataform + ", email=" + email + ", phone_number=" + phone_number + ", full_name=" + full_name + ", sup_encargado=" + sup_encargado + '}';
    }

    

   

}
