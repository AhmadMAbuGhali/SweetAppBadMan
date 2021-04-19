package com.example.sweetapp.Adapter;

public class ChaletListItemAdapter {
private int img_Chalet;
private String name_Chalet ,Salary,Title_Chalet,Thenumberofhours_Chalet;
private Float Evaluation_Chalet;


    public ChaletListItemAdapter(int img_Chalet, String name_Chalet, String salary, String title_Chalet, Float evaluation_Chalet, String thenumberofhours_Chalet) {
        this.img_Chalet = img_Chalet;
        this.name_Chalet = name_Chalet;
        Salary = salary;
        Title_Chalet = title_Chalet;
        Evaluation_Chalet = evaluation_Chalet;
        Thenumberofhours_Chalet = thenumberofhours_Chalet;
    }

    public ChaletListItemAdapter() {
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

    public String getTitle_Chalet() {
        return Title_Chalet;
    }

    public void setTitle_Chalet(String title_Chalet) {
        Title_Chalet = title_Chalet;
    }

    public Float getEvaluation_Chalet() {
        return Evaluation_Chalet;
    }

    public void setEvaluation_Chalet(Float evaluation_Chalet) {
        Evaluation_Chalet = evaluation_Chalet;
    }

    public String getThenumberofhours_Chalet() {
        return Thenumberofhours_Chalet;
    }

    public void setThenumberofhours_Chalet(String thenumberofhours_Chalet) {
        Thenumberofhours_Chalet = thenumberofhours_Chalet;
    }
}
