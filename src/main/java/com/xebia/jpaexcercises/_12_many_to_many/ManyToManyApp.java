package com.xebia.jpaexcercises._12_many_to_many;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ManyToManyApp {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = null;
        EntityManager entityManager = null;
        try {
            entityManagerFactory = Persistence.createEntityManagerFactory("jpa-examples-pu");
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();

            Project project=new Project("java");
            entityManager.persist(project);
            entityManager.getTransaction().commit();
            entityManager.close();

            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            Employee employee = new Employee("Test Employee", 1_000_000);
            employee.addProject(project);
            Employee employee2 = new Employee("Test Employee 2", 1_5_000);
            employee2.addProject(project);
            entityManager.persist(employee);
            entityManager.persist(employee2);
            entityManager.getTransaction().commit();

            Employee foundEmployee = entityManager.find(Employee.class, employee.getId());
            System.out.println(foundEmployee);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);

        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }

    }
}
