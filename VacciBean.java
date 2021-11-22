/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import javax.ejb.Stateless;

/**
 *
 * @author user
 */
@Stateless
public class VacciBean {
    //bean class for userview vaccination details,ashaviewGovProjects,Asha And User View Panchayath Projects,user_food_details
private String vacci_id,vacci_name,vacci_details,pro_name,pro_user,pro_det,pro_date,ProgName,PanProLoc,PanProDate,progtime,ProgDet;
private String FoodName,FoodPeriod,FoodDetails ;

    public String getFoodName() {
        return FoodName;
    }

    public void setFoodName(String FoodName) {
        this.FoodName = FoodName;
    }

    public String getFoodPeriod() {
        return FoodPeriod;
    }

    public void setFoodPeriod(String FoodPeriod) {
        this.FoodPeriod = FoodPeriod;
    }

    public String getFoodDetails() {
        return FoodDetails;
    }

    public void setFoodDetails(String FoodDetails) {
        this.FoodDetails = FoodDetails;
    }
    public String getProgName() {
        return ProgName;
    }

    public void setProgName(String ProgName) {
        this.ProgName = ProgName;
    }

    public String getPanProLoc() {
        return PanProLoc;
    }

    public void setPanProLoc(String PanProLoc) {
        this.PanProLoc = PanProLoc;
    }

    public String getPanProDate() {
        return PanProDate;
    }

    public void setPanProDate(String PanProDate) {
        this.PanProDate = PanProDate;
    }

    public String getProgtime() {
        return progtime;
    }

    public void setProgtime(String progtime) {
        this.progtime = progtime;
    }

    public String getProgDet() {
        return ProgDet;
    }

    public void setProgDet(String ProgDet) {
        this.ProgDet = ProgDet;
    }

    public String getPro_name() {
        return pro_name;
    }

    public void setPro_name(String pro_name) {
        this.pro_name = pro_name;
    }

    public String getPro_user() {
        return pro_user;
    }

    public void setPro_user(String pro_user) {
        this.pro_user = pro_user;
    }

    public String getPro_det() {
        return pro_det;
    }

    public void setPro_det(String pro_det) {
        this.pro_det = pro_det;
    }

    public String getPro_date() {
        return pro_date;
    }

    public void setPro_date(String pro_date) {
        this.pro_date = pro_date;
    }

    public String getVacci_id() {
        return vacci_id;
    }

    public void setVacci_id(String vacci_id) {
        this.vacci_id = vacci_id;
    }

    public String getVacci_name() {
        return vacci_name;
    }

    public void setVacci_name(String vacci_name) {
        this.vacci_name = vacci_name;
    }

    public String getVacci_details() {
        return vacci_details;
    }

    public void setVacci_details(String vacci_details) {
        this.vacci_details = vacci_details;
    }
}
