package com.mainmenu;

import java.util.List;
import java.util.Scanner;

import com.database.HibernateConnection;
import com.entity.Account;
import com.entity.Bank;
import com.entity.Branch;
import com.entity.Customer;
import com.entity.Loan;
import com.repository.AccountRepository;
import com.repository.BankRepository;
import com.repository.BranchRepository;
import com.repository.CustomerRepository;
import com.repository.LoanRepository;

import jakarta.persistence.EntityManager;

public class MainMenu {

    private static BankRepository bankRepository = new BankRepository();
    private static BranchRepository branchRepository = new BranchRepository();
    private static CustomerRepository customerRepository = new CustomerRepository();
    private static AccountRepository accountRepository = new AccountRepository();
    private static LoanRepository loanRepository = new LoanRepository();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        System.out.println("=====================================");
        System.out.println("  Banking Management System");
        System.out.println("=====================================\n");

        HibernateConnection.checkConnection();
        System.out.println();

        boolean running = true;
        while (running) {
            displayMenu();
            System.out.print("Enter your choice: ");

            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        addBank(scanner);
                        break;
                    case 2:
                        removeBank(scanner);
                        break;
                    case 3:
                        updateBank(scanner);
                        break;
                    case 4:
                        findBank(scanner);
                        break;
                    case 5:
                        addBranch(scanner);
                        break;
                    case 6:
                        addCustomer(scanner);
                        break;
                    case 7:
                        addAccount(scanner);
                        break;
                    case 8:
                        addLoan(scanner);
                        break;
                    case 9:
                        viewAllBanks();
                        break;
                    case 10:
                        viewAllBranches();
                        break;
                    case 11:
                        viewAllCustomers();
                        break;
                    case 12:
                        viewAllAccounts();
                        break;
                    case 13:
                        viewAllLoans();
                        break;
                    case 0:
                        System.out.println("\nThank you for using Banking Management System!");
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid choice! Please try again.\n");
                }
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter a number.\n");
                scanner.nextLine(); // Clear the buffer
            }
        }

        HibernateConnection.close();
        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("\n===== MENU =====");
        System.out.println("1. Add Bank");
        System.out.println("2. Remove Bank");
        System.out.println("3. Update Bank");
        System.out.println("4. Find Bank");
        System.out.println("5. Add Branch");
        System.out.println("6. Add Customer");
        System.out.println("7. Add Account");
        System.out.println("8. Add Loan");
        System.out.println("9. View All Banks");
        System.out.println("10. View All Branches");
        System.out.println("11. View All Customers");
        System.out.println("12. View All Accounts");
        System.out.println("13. View All Loans");
        System.out.println("0. Exit");
        System.out.println("================");
    }

    // Bank Operations
    private static void addBank(Scanner scanner) {
        System.out.println("\n--- Add Bank ---");
        System.out.print("Enter Bank Code: ");
        String code = scanner.nextLine();
        System.out.print("Enter Bank Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Bank Address: ");
        String address = scanner.nextLine();

        Bank bank = new Bank(code, name, address);
        bankRepository.addBank(bank);
        System.out.println();
    }

    private static void removeBank(Scanner scanner) {
        System.out.println("\n--- Remove Bank ---");
        System.out.print("Enter Bank Code: ");
        String code = scanner.nextLine();
        bankRepository.removeBank(code);
        System.out.println();
    }

    private static void updateBank(Scanner scanner) {
        System.out.println("\n--- Update Bank ---");
        System.out.print("Enter Bank Code: ");
        String code = scanner.nextLine();
        System.out.print("Enter New Bank Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter New Bank Address: ");
        String address = scanner.nextLine();

        Bank bank = new Bank(code, name, address);
        bankRepository.updateBank(bank);
        System.out.println();
    }

    private static void findBank(Scanner scanner) {
        System.out.println("\n--- Find Bank ---");
        System.out.print("Enter Bank Code: ");
        String code = scanner.nextLine();
        bankRepository.findBank(code);
        System.out.println();
    }

    private static void viewAllBanks() {
        System.out.println("\n--- All Banks ---");
        List<Bank> banks = bankRepository.getAllBanks();
        if (banks.isEmpty()) {
            System.out.println("No banks found.");
        } else {
            for (Bank bank : banks) {
                System.out.println(bank);
            }
        }
        System.out.println();
    }

    // Branch Operations
    private static void addBranch(Scanner scanner) {
        System.out.println("\n--- Add Branch ---");
        System.out.print("Enter Bank Code (must exist): ");
        String bankCode = scanner.nextLine();

        EntityManager em = HibernateConnection.getEntityManager();
        Bank bank = em.find(Bank.class, bankCode);
        em.close();

        if (bank == null) {
            System.out.println("Bank not found!");
            System.out.println();
            return;
        }

        System.out.print("Enter Branch Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Branch Address: ");
        String address = scanner.nextLine();

        Branch branch = new Branch(name, address, bank);
        branchRepository.addBranch(branch);
        System.out.println();
    }

    private static void viewAllBranches() {
        System.out.println("\n--- All Branches ---");
        List<Branch> branches = branchRepository.getAllBranches();
        if (branches.isEmpty()) {
            System.out.println("No branches found.");
        } else {
            for (Branch branch : branches) {
                System.out.println(branch);
            }
        }
        System.out.println();
    }

    // Customer Operations
    private static void addCustomer(Scanner scanner) {
        System.out.println("\n--- Add Customer ---");
        System.out.print("Enter Customer Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Customer Address: ");
        String address = scanner.nextLine();
        System.out.print("Enter Customer Phone: ");
        String phone = scanner.nextLine();

        Customer customer = new Customer(name, address, phone);
        customerRepository.addCustomer(customer);
        System.out.println();
    }

    private static void viewAllCustomers() {
        System.out.println("\n--- All Customers ---");
        List<Customer> customers = customerRepository.getAllCustomers();
        if (customers.isEmpty()) {
            System.out.println("No customers found.");
        } else {
            for (Customer customer : customers) {
                System.out.println(customer);
            }
        }
        System.out.println();
    }

    // Account Operations
    private static void addAccount(Scanner scanner) {
        System.out.println("\n--- Add Account ---");
        System.out.print("Enter Account Number: ");
        String accountNo = scanner.nextLine();
        System.out.print("Enter Account Type (Saving/Checking/Business): ");
        String accType = scanner.nextLine();
        System.out.print("Enter Initial Balance: ");
        Double balance = Double.parseDouble(scanner.nextLine());

        Account account = new Account(accountNo, accType, balance);
        accountRepository.addAccount(account);
        System.out.println();
    }

    private static void viewAllAccounts() {
        System.out.println("\n--- All Accounts ---");
        List<Account> accounts = accountRepository.getAllAccounts();
        if (accounts.isEmpty()) {
            System.out.println("No accounts found.");
        } else {
            for (Account account : accounts) {
                System.out.println(account);
            }
        }
        System.out.println();
    }

    // Loan Operations
    private static void addLoan(Scanner scanner) {
        System.out.println("\n--- Add Loan ---");
        System.out.print("Enter Loan Type (Home/Personal/Auto/Business): ");
        String loanType = scanner.nextLine();
        System.out.print("Enter Loan Amount: ");
        Double amount = Double.parseDouble(scanner.nextLine());

        Loan loan = new Loan(loanType, amount);
        loanRepository.addLoan(loan);
        System.out.println();
    }

    private static void viewAllLoans() {
        System.out.println("\n--- All Loans ---");
        List<Loan> loans = loanRepository.getAllLoans();
        if (loans.isEmpty()) {
            System.out.println("No loans found.");
        } else {
            for (Loan loan : loans) {
                System.out.println(loan);
            }
        }
        System.out.println();
    }
}
