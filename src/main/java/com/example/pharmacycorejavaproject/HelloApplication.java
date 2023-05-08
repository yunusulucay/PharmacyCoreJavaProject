package com.example.pharmacycorejavaproject;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

import java.util.Scanner;

public class HelloApplication {
    public static void main(String[] args) {

         EntityBasic entityBasic = new EntityBasic();
         entityBasic.mainRequest();

    }
}