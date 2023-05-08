package com.example.pharmacycorejavaproject;

import jakarta.persistence.EntityManager;

import java.util.Scanner;

public class EntityBasic extends PharmacyRequests {
    void mainRequest() {
        DatabaseTransactions databaseTransactions = new DatabaseTransactions();
        CustomerTransactions customerTransactions = new CustomerTransactions();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Admin Login : 1\nCustomer : 2\nChoose the transaction : ");
        int transaction = scanner.nextInt();

        switch (transaction) {
            case 1:
                System.out.println("You are in login section");
                if (databaseTransactions.loginRequest()) {
                    System.out.println("You have logged in successfully as admin!");
                    System.out.println();
                    requests();
                } else {
                    System.out.println("Your username or/and password is wrong!");
                }
                return;
            case 2:
                System.out.println("You are in customer side");
                customerTransactions.buyDrug();
                return;
            default:
                System.out.println("Wrong choice");
                return;
        }
    }




}
