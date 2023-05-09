package com.example.pharmacycorejavaproject;

import entity.Companymoney;
import entity.Pharmacy;
import entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.Scanner;

public class CustomerTransactions extends DatabaseTransactions {
    CustomerTransactions() {
        //
    }

    void buyDrug() {
        //while (scanner.nextInt() != 0)
        Scanner scanner = new Scanner(System.in);
        while (true) {
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            EntityTransaction transaction = entityManager.getTransaction();

            System.out.println("Enter drug id / To exit enter 0 : ");
            int id = scanner.nextInt();
            if (id == 0) {
                break;
            }
            System.out.println("Enter count : ");
            int count = scanner.nextInt();

            try {
                transaction.begin();

                Pharmacy pharmacy1 = entityManager.createQuery("SELECT p FROM Pharmacy p WHERE p.id = ?1", Pharmacy.class)
                        .setParameter(1, id)
                        .getSingleResult();

                Companymoney companymoney1 = entityManager.createQuery("SELECT c FROM Companymoney c WHERE c.id = 1", Companymoney.class)
                        .getSingleResult();

                // Updating Pharmacy table
                entityManager.createNativeQuery("UPDATE Pharmacy p " +
                                "SET p.stock = ? WHERE id = ?")
                        .setParameter(1, pharmacy1.getStock() - count)
                        .setParameter(2, id)
                        .executeUpdate();

                // Updating Companymoney table
                entityManager.createNativeQuery("UPDATE Companymoney c " +
                                "SET c.companymoney = ?1 WHERE id = 1")
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
}
