package com.repository;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import com.database.HibernateConnection;
import com.entity.Account;

public class AccountRepository {

    public void addAccount(Account account) {
        EntityManager em = HibernateConnection.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(account);
            transaction.commit();
            System.out.println("Account added successfully!");
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            System.out.println("Error adding account: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public void removeAccount(String accountNo) {
        EntityManager em = HibernateConnection.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Account account = em.find(Account.class, accountNo);
            if (account != null) {
                em.remove(account);
                transaction.commit();
                System.out.println("Account removed successfully!");
            } else {
                System.out.println("Account not found!");
            }
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            System.out.println("Error removing account: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public void updateAccount(Account account) {
        EntityManager em = HibernateConnection.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.merge(account);
            transaction.commit();
            System.out.println("Account updated successfully!");
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            System.out.println("Error updating account: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public void findAccount(String accountNo) {
        EntityManager em = HibernateConnection.getEntityManager();
        try {
            Account account = em.find(Account.class, accountNo);
            if (account != null) {
                System.out.println("Account Found: " + account);
            } else {
                System.out.println("No account found with number: " + accountNo);
            }
        } catch (Exception e) {
            System.out.println("Error finding account: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public List<Account> getAllAccounts() {
        EntityManager em = HibernateConnection.getEntityManager();
        try {
            return em.createQuery("SELECT a FROM Account a", Account.class).getResultList();
        } finally {
            em.close();
        }
    }
}
