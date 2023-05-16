package com.example.pharmacycorejavaproject;
import com.example.pharmacycorejavaproject.interfaces.EntityBasicInterface;
import java.util.Scanner;

public class EntityBasic extends PharmacyRequests implements EntityBasicInterface {
    public void mainRequest() {
        DatabaseTransactions databaseTransactions = new DatabaseTransactions();
        CustomerTransactions customerTransactions = new CustomerTransactions();
        boolean continueLoop = true;
        Scanner scanner = new Scanner(System.in);
        while (continueLoop) {
            System.out.println("Admin Login : 1\nCustomer : 2\nTo Exit : 0\nChoose the transaction : ");
            int transaction = scanner.nextInt();

            switch (transaction) {
                case 0:
                    continueLoop = false;
                    break;
                case 1:
                    System.out.println("You are in login section");
                    if (databaseTransactions.loginRequest()) {
                        System.out.println("You have logged in successfully as admin!");
                        System.out.println();
                        requests();
                    } else {
                        System.out.println("Your username or/and password is wrong!");
                    }
                    break;
                case 2:
                    System.out.println("You are in customer side");
                    customerTransactions.buyDrug();
                    break;
                default:
                    System.out.println("Wrong choice");
                    break;
            }
        }
    }
}
