package com.example.pharmacycorejavaproject;

import entity.Pharmacy;

import java.util.List;
import java.util.Scanner;

public class PharmacyRequests extends DatabaseTransactions {
    int choice;
    PharmacyRequests() {
        //
    }
    void requests () {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Add Drug : 1\nDelete Drug : 2\nEdit Drug : 3\nView Drug : 4\nChoose the transaction : ");
        choice = scanner.nextInt();

        int id;
        int userId;
        String drugName;
        double price;
        int stock;

        switch (choice) {
            case 1:
                System.out.println("Enter id : \nEnter user id : \nEnter drug name : \nEnter price : \nEnter stock : ");
                id = scanner.nextInt();
                userId = scanner.nextInt();
                scanner.nextLine();
                drugName = scanner.nextLine();
                price = scanner.nextDouble();
                stock = scanner.nextInt();

                addDrug(id, userId, drugName, price, stock);
                return;
            case 2:
                System.out.println("Enter id : ");
                id = scanner.nextInt();
                deleteDrug(id);
                return;
            case 3:
                System.out.println("Enter id : \nEnter user id : \nEnter drug name : \nEnter price : \nEnter stock : ");
                id = scanner.nextInt();
                userId = scanner.nextInt();
                scanner.nextLine();
                drugName = scanner.nextLine();
                price = scanner.nextDouble();
                stock = scanner.nextInt();

                editDrug(id, userId, drugName, price, stock);
                return;
            case 4:
                System.out.println("Enter id to view a record / Enter 0 to view all records : ");
                id = scanner.nextInt();
                if (id == 0) {
                    viewDrug();
                } else {
                    viewDrug(id);
                }
                return;
        }
    }

    void addDrug(int id, int userId, String drugName, double price, int stock) {
        try {
            transaction.begin();

            entityManager.createNativeQuery("INSERT INTO Pharmacy VALUES(?,?,?,?,?)")
                    .setParameter(1, id)
                    .setParameter(2, userId)
                    .setParameter(3, drugName)
                    .setParameter(4, price)
                    .setParameter(5, stock)
                    .executeUpdate();

            transaction.commit();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }
    }

    void deleteDrug(int id) {
        try {
            transaction.begin();

            entityManager.createNativeQuery("DELETE FROM Pharmacy p WHERE p.id = ?")
                    .setParameter(1, id)
                    .executeUpdate();

            transaction.commit();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }
    }

    void editDrug(int id, int userId, String drugName, double price, int stock) {
        try {
            transaction.begin();

            entityManager.createNativeQuery("UPDATE Pharmacy p " +
                            "SET p.id = ?, p.userid = ?, p.drugname = ?, p.price = ?, p.stock = ?")
                    .setParameter(1, id)
                    .setParameter(2, userId)
                    .setParameter(3, drugName)
                    .setParameter(4, price)
                    .setParameter(5, stock)
                    .executeUpdate();

            transaction.commit();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }
    }

    void viewDrug() {
        try {
            transaction.begin();

            List<Pharmacy> pharmacyList = entityManager.createQuery("SELECT e FROM Pharmacy e", Pharmacy.class).getResultList();
            for(Pharmacy element : pharmacyList) {
                System.out.println("DrugName : " + element.getDrugname());
                System.out.println("Price : " + element.getPrice());
                System.out.println("Stock : " + element.getStock());
                System.out.println("-------------------------------");
            }

            transaction.commit();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }
    }

    void viewDrug(int intId) {
        try {
            transaction.begin();

            List<Pharmacy> pharmacyList = entityManager.createQuery("SELECT e FROM Pharmacy e", Pharmacy.class).getResultList();
            for(Pharmacy element : pharmacyList) {
                System.out.println("DrugName : " + element.getDrugname());
                System.out.println("Price : " + element.getPrice());
                System.out.println("Stock : " + element.getStock());
            }

            transaction.commit();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }
    }


}
