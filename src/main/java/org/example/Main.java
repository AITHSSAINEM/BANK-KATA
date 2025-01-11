package org.example;

import org.example.entities.Account;

public class Main {
    public static void main(String[] args) {
        Account account = new Account();

        try {
            account.deposit(1000); // Dépôt de 1000
            account.deposit(2000); // Dépôt de 2000
            account.withdraw(500); // Retrait de 500
            account.printStatement(); // Affichage du relevé
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.err.println("Erreur: " + e.getMessage());
        }
    }
}
