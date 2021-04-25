package com.example.sweetapp.Model;

public class ChaletListIteamModel {
private int img_Chalet;
private String chaletId, name_Chalet ,Salary, address_Chalet, numOfHours,numofphone,chaletOwnerId;
private Float Evaluation_Chalet;




    public ChaletListIteamModel(int img_Chalet, String chaletId, String name_Chalet, String salary, String address_Chalet, Float evaluation_Chalet, String numOfHours, String numofphone, String chaletOwnerId) {
            this.chaletId = chaletId;
            this.chaletOwnerId = chaletOwnerId;
        this.img_Chalet = img_Chalet;
        this.name_Chalet = name_Chalet;
        this.Salary = salary;
        this.address_Chalet = address_Chalet;
        this.Evaluation_Chalet = evaluation_Chalet;
        this.numOfHours = numOfHours;
        this.numofphone = numofphone;
    }

    public String getChaletId() {
        return chaletId;
    }

    public void setChaletId(String chaletId) {
        this.chaletId = chaletId;
    }

    public String getChaletOwnerId() {
        return chaletOwnerId;
    }

    public void setChaletOwnerId(String chaletOwnerId) {
        this.chaletOwnerId = chaletOwnerId;
    }
    public ChaletListIteamModel() {
    }

    public String getNumofphone() {
        return numofphone;
    }

    public void setNumofphone(String numofphone) {
        this.numofphone = numofphone;
    }



    public int getImg_Chalet() {
        return img_Chalet;
    }

    public void setImg_Chalet(int img_Chalet) {
        this.img_Chalet = img_Chalet;
    }

    public String getName_Chalet() {
        return name_Chalet;
    }

    public void setName_Chalet(String name_Chalet) {
        this.name_Chalet = name_Chalet;
    }

    public String getSalary() {
        return Salary;
    }

    public void setSalary(String salary) {
        Salary = salary;
    }

    public String getAddress_Chalet() {
        return address_Chalet;
    }

    public void setAddress_Chalet(String address_Chalet) {
        this.address_Chalet = address_Chalet;
    }

    public Float getEvaluation_Chalet() {
        return Evaluation_Chalet;
    }

    public void setEvaluation_Chalet(Float evaluation_Chalet) {
        Evaluation_Chalet = evaluation_Chalet;
    }

    public String getNumOfHours() {
        return numOfHours;
    }

    public void setNumOfHours(String numOfHours) {
        this.numOfHours = numOfHours;
    }
}
