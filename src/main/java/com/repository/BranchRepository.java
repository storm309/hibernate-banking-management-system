package com.repository;

import java.util.List;

import com.database.HibernateConnection;
import com.entity.Branch;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class BranchRepository {

    public void addBranch(Branch branch) {
        EntityManager em = HibernateConnection.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(branch);
            transaction.commit();
            System.out.println("Branch added successfully!");
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            System.out.println("Error adding branch: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public void removeBranch(Long branchId) {
        EntityManager em = HibernateConnection.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Branch branch = em.find(Branch.class, branchId);
            if (branch != null) {
                em.remove(branch);
                transaction.commit();
                System.out.println("Branch removed successfully!");
            } else {
                System.out.println("Branch not found!");
            }
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            System.out.println("Error removing branch: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public void updateBranch(Branch branch) {
        EntityManager em = HibernateConnection.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.merge(branch);
            transaction.commit();
            System.out.println("Branch updated successfully!");
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            System.out.println("Error updating branch: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public void findBranch(Long branchId) {
        EntityManager em = HibernateConnection.getEntityManager();
        try {
            Branch branch = em.find(Branch.class, branchId);
            if (branch != null) {
                System.out.println("Branch Found: " + branch);
            } else {
                System.out.println("No branch found with ID: " + branchId);
            }
        } catch (Exception e) {
            System.out.println("Error finding branch: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public List<Branch> getAllBranches() {
        EntityManager em = HibernateConnection.getEntityManager();
        try {
            return em.createQuery("SELECT b FROM Branch b", Branch.class).getResultList();
        } finally {
            em.close();
        }
    }
}
