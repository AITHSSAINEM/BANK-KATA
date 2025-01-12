package org.example.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Account implements AccountService {
    private final List<Transaction> transactions = new ArrayList<>();
    private int balance = 0;
    private static final int DAILY_WITHDRAWAL_LIMIT = 20000;

    @Override
    public void deposit(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Le montant du dépôt doit être positif.");
        }
        balance += amount;
        transactions.add(new Transaction(amount, LocalDate.now()));
    }

    @Override
    public void withdraw(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Le montant du retrait doit être positif.");
        }
        if (amount > balance) {
            throw new IllegalStateException("Fonds insuffisants pour effectuer ce retrait.");
        }

        int totalWithdrawnToday = getTotalWithdrawnToday();
        if (totalWithdrawnToday + amount > DAILY_WITHDRAWAL_LIMIT) {
            throw new IllegalStateException("Limite de retrait journalier de " + DAILY_WITHDRAWAL_LIMIT + " atteinte.");
        }

        balance -= amount;
        transactions.add(new Transaction(-amount, LocalDate.now()));
    }

    @Override
    public void printStatement() {
        System.out.println("DATE       | MONTANT | SOLDE");
        int runningBalance = balance;
        List<Transaction> reversedTransactions = new ArrayList<>(transactions);
        Collections.reverse(reversedTransactions);
        for (Transaction transaction : reversedTransactions) {
            System.out.printf("%s | %d     | %d%n", transaction.getDate(), transaction.getAmount(), runningBalance);
            runningBalance -= transaction.getAmount();
        }
    }
    public int getBalance() {
        return balance;
    }

    // Nouvelle méthode pour calculer les retraits quotidiens
    private int getTotalWithdrawnToday() {
        LocalDate today = LocalDate.now();
        return transactions.stream()
                .filter(transaction -> transaction.getDate().equals(today) && transaction.getAmount() < 0)
                .mapToInt(Transaction::getAmount)
                .sum() * -1; // Convertir les valeurs négatives en positives
    }
}