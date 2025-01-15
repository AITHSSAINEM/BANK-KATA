# Bank Kata

Ce projet est une application Java simple qui simule un système de compte bancaire. Elle permet d'effectuer des dépôts, des retraits et d'afficher un relevé des transactions. L'application impose une limite journalière de retrait et gère les exceptions pour les opérations non valides.

---

## Fonctionnalités

- **Dépôt** : Ajouter de l'argent à votre compte.
- **Retrait** : Retirer de l'argent du compte avec une limite quotidienne de **20 000**.
- **Relevé de compte** : Afficher l'historique des transactions, avec les plus récentes en haut.
- **Gestion des exceptions** : Validation des montants et des limites pour éviter les erreurs.

---

## Prérequis

- **Java 21** ou version ultérieure.
- **Maven** pour la gestion des dépendances et la construction du projet.

---

## Installation

1. **Cloner le dépôt :**
   ```bash
   git clone https://github.com/AITHSSAINEM/BANK-KATA.git
   cd bank-kata
   ```
2. **Construire le projet :**
    ```bash
   mvn clean install
   ```
3. **Lancer les tests :**
     ```bash
   mvn test
   ```

