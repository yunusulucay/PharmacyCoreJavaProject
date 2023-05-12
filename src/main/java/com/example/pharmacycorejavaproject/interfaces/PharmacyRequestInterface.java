package com.example.pharmacycorejavaproject.interfaces;

public interface PharmacyRequestInterface {

    void requests();
    void addDrug(int id, int userId, String drugName, double price, int stock);
    void deleteDrug(int id);
    void editDrug(int id, int userId, String drugName, double price, int stock);
    void viewDrug();
    void viewDrug(int intId);


}
