package com.xebia.jpaexcercises._08_many_to_one;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ManyToOneApp {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = null;
        EntityManager entityManager = null;
        try {
            entityManagerFactory = Persistence.createEntityManagerFactory("jpa-examples-pu");
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();

            Employee employee1 = new Employee();
            Employee employee2=new Employee();
            employee1.setName("John");
            employee2.setName("Tom");
            employee1.setSalary(100_000);
            employee2.setSalary(200000);

            Department department = new Department("IT");
            employee1.setDepartment(department);
            employee2.setDepartment(department);
            entityManager.persist(employee1);
            entityManager.persist(employee2);
            entityManager.getTransaction().commit();

            Employee foundEmployee = entityManager.find(Employee.class, employee1.getId());
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
