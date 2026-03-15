package com.repository;

import java.util.List;

import com.database.HibernateConnection;
import com.entity.Loan;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class LoanRepository {

    public void addLoan(Loan loan) {
        EntityManager em = HibernateConnection.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(loan);
            transaction.commit();
            System.out.println("Loan added successfully!");
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            System.out.println("Error adding loan: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public void removeLoan(Long loanId) {
        EntityManager em = HibernateConnection.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Loan loan = em.find(Loan.class, loanId);
            if (loan != null) {
                em.remove(loan);
                transaction.commit();
                System.out.println("Loan removed successfully!");
            } else {
                System.out.println("Loan not found!");
            }
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            System.out.println("Error removing loan: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public void updateLoan(Loan loan) {
        EntityManager em = HibernateConnection.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.merge(loan);
            transaction.commit();
            System.out.println("Loan updated successfully!");
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            System.out.println("Error updating loan: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public void findLoan(Long loanId) {
        EntityManager em = HibernateConnection.getEntityManager();
        try {
            Loan loan = em.find(Loan.class, loanId);
            if (loan != null) {
                System.out.println("Loan Found: " + loan);
            } else {
                System.out.println("No loan found with ID: " + loanId);
            }
        } catch (Exception e) {
            System.out.println("Error finding loan: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public List<Loan> getAllLoans() {
        EntityManager em = HibernateConnection.getEntityManager();
        try {
            return em.createQuery("SELECT l FROM Loan l", Loan.class).getResultList();
        } finally {
            em.close();
        }
    }
}
