package com.example.pharmacycorejavaproject;

import com.example.pharmacycorejavaproject.interfaces.DatabaseTransactionInterface;
import entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import java.util.Scanner;

public class DatabaseTransactions implements DatabaseTransactionInterface {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    EntityTransaction transaction = entityManager.getTransaction();
    Scanner scanner = new Scanner(System.in);

    DatabaseTransactions() {
        //
    }

    public boolean loginRequest() {
        System.out.println("Enter your username : ");
        String username = scanner.nextLine();
        System.out.println("Enter your password : ");
        String password = scanner.nextLine();

        boolean bool;
        try {
            transaction.begin();

            bool = entityManager.createQuery("SELECT e FROM User e WHERE e.username = '" + username +
                    "' AND e.password = '" + password + "'", User.class).getResultList().size() >= 1;

            transaction.commit();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }

        //return (count == 1) ? true : false;
        return bool;
    }

}
