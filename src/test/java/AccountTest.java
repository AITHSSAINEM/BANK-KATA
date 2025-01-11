import org.example.entities.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    public Account account;

    @BeforeEach
    void setUp() {
        account = new Account();
    }

    @Test
    void testDepot() {
        account.deposit(1000);
        assertEquals(1000, account.getBalance(), "Le solde devrait être 1000 après un dépôt de 1000.");

        account.deposit(2000);
        assertEquals(3000, account.getBalance(), "Le solde devrait être 3000 après un dépôt additionnel de 2000.");
    }

    @Test
    void testRetrait() {
        account.deposit(3000);
        account.withdraw(500);
        assertEquals(2500, account.getBalance(), "Le solde devrait être 2500 après un retrait de 500.");

        account.withdraw(1000);
        assertEquals(1500, account.getBalance(), "Le solde devrait être 1500 après un retrait additionnel de 1000.");
    }

    @Test
    void testRetraitAvecFondsInsuffisants() {
        account.deposit(1000);
        Exception exception = assertThrows(IllegalStateException.class, () -> account.withdraw(2000));
        assertEquals("Fonds insuffisants pour effectuer ce retrait.", exception.getMessage());
        assertEquals(1000, account.getBalance(), "Le solde ne doit pas changer après un retrait échoué.");
    }

    @Test
    void testDepotMontantInvalide() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> account.deposit(-100));
        assertEquals("Le montant du dépôt doit être positif.", exception.getMessage());
        assertEquals(0, account.getBalance(), "Le solde ne doit pas changer après un dépôt invalide.");

        exception = assertThrows(IllegalArgumentException.class, () -> account.deposit(0));
        assertEquals("Le montant du dépôt doit être positif.", exception.getMessage());
        assertEquals(0, account.getBalance(), "Le solde ne doit pas changer après un dépôt de 0.");
    }

    @Test
    void testRetraitMontantInvalide() {
        account.deposit(1000);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> account.withdraw(-100));
        assertEquals("Le montant du retrait doit être positif.", exception.getMessage());
        assertEquals(1000, account.getBalance(), "Le solde ne doit pas changer après un retrait invalide.");

        exception = assertThrows(IllegalArgumentException.class, () -> account.withdraw(0));
        assertEquals("Le montant du retrait doit être positif.", exception.getMessage());
        assertEquals(1000, account.getBalance(), "Le solde ne doit pas changer après un retrait de 0.");
    }

    @Test
    void testAucuneTransaction() {
        assertEquals(0, account.getBalance(), "Le solde initial devrait être 0.");
        assertDoesNotThrow(account::printStatement, "L'affichage des transactions ne doit pas lever d'exception même sans transactions.");
    }
}
