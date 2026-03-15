package com.repository;

import java.util.List;

import com.database.HibernateConnection;
import com.entity.Customer;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class CustomerRepository {

    public void addCustomer(Customer customer) {
        EntityManager em = HibernateConnection.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(customer);
            transaction.commit();
            System.out.println("Customer added successfully!");
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            System.out.println("Error adding customer: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public void removeCustomer(Long customerId) {
        EntityManager em = HibernateConnection.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Customer customer = em.find(Customer.class, customerId);
            if (customer != null) {
                em.remove(customer);
                transaction.commit();
                System.out.println("Customer removed successfully!");
            } else {
                System.out.println("Customer not found!");
            }
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            System.out.println("Error removing customer: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public void updateCustomer(Customer customer) {
        EntityManager em = HibernateConnection.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.merge(customer);
            transaction.commit();
            System.out.println("Customer updated successfully!");
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            System.out.println("Error updating customer: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public void findCustomer(Long customerId) {
        EntityManager em = HibernateConnection.getEntityManager();
        try {
            Customer customer = em.find(Customer.class, customerId);
            if (customer != null) {
                System.out.println("Customer Found: " + customer);
            } else {
                System.out.println("No customer found with ID: " + customerId);
            }
        } catch (Exception e) {
            System.out.println("Error finding customer: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public List<Customer> getAllCustomers() {
        EntityManager em = HibernateConnection.getEntityManager();
        try {
            return em.createQuery("SELECT c FROM Customer c", Customer.class).getResultList();
        } finally {
            em.close();
        }
    }
}
