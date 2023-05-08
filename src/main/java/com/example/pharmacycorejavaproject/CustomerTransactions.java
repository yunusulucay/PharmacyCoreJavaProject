package com.example.pharmacycorejavaproject;

import entity.Companymoney;
import entity.Pharmacy;
import entity.User;

import java.util.Scanner;

public class CustomerTransactions extends DatabaseTransactions {
    CustomerTransactions() {
        //
    }

    void buyDrug() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter drug id : ");
        System.out.println("Enter count : ");
        int id = scanner.nextInt();
        int count = scanner.nextInt();

        try {
            transaction.begin();

            Pharmacy pharmacy1 = entityManager.createQuery("SELECT p FROM Pharmacy p WHERE p.id = ?", Pharmacy.class)
                    .setParameter(1, id)
                    .getSingleResult();
            //System.out.println(pharmacy1.getPrice());
            //System.out.println(pharmacy1.getStock());

            Companymoney companymoney1 = entityManager.createQuery("SELECT c FROM Companymoney c WHERE c.id = 1", Companymoney.class)
                    .getSingleResult();
            //System.out.println(companymoney1.getCompanymoney());

            // Updating Pharmacy table
            entityManager.createNativeQuery("UPDATE Pharmacy p " +
                            "SET p.stock = ? WHERE id = ?")
                    .setParameter(1, pharmacy1.getStock() - count)
                    .setParameter(2, id)
                    .executeUpdate();

            // Updating Companymoney table
            entityManager.createNativeQuery("UPDATE Companymoney c" +
                            "SET c.companymoney = ? WHERE id = 1")
                    .setParameter(1, (pharmacy1.getPrice() * count) + companymoney1.getCompanymoney())
                    .executeUpdate();

            System.out.println("You bought " + count + " " + pharmacy1.getDrugname() + ". The price you paid is " + pharmacy1.getPrice() * count + ".");

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
