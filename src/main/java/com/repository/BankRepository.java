package com.repository;

import java.util.List;

import com.database.HibernateConnection;
import com.entity.Bank;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class BankRepository {

    public void addBank(Bank bank) {
        EntityManager em = HibernateConnection.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(bank);
            transaction.commit();
            System.out.println("Bank added successfully!");
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            System.out.println("Error adding bank: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public void removeBank(String code) {
        EntityManager em = HibernateConnection.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Bank bank = em.find(Bank.class, code);
            if (bank != null) {
                em.remove(bank);
                transaction.commit();
                System.out.println("Bank removed successfully!");
            } else {
                System.out.println("Bank not found!");
            }
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            System.out.println("Error removing bank: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public void updateBank(Bank bank) {
        EntityManager em = HibernateConnection.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.merge(bank);
            transaction.commit();
            System.out.println("Bank updated successfully!");
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            System.out.println("Error updating bank: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public void findBank(String code) {
        EntityManager em = HibernateConnection.getEntityManager();
        try {
            Bank bank = em.find(Bank.class, code);
            if (bank != null) {
                System.out.println("Bank Found: " + bank);
            } else {
                System.out.println("No bank found with code: " + code);
            }
        } catch (Exception e) {
            System.out.println("Error finding bank: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public List<Bank> getAllBanks() {
        EntityManager em = HibernateConnection.getEntityManager();
        try {
            return em.createQuery("SELECT b FROM Bank b", Bank.class).getResultList();
        } finally {
            em.close();
        }
    }
}
