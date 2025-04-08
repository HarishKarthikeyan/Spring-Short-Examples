package org.jh;

import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.jh.entities.Student;
import org.jh.persistence.CustomPersistence;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        Map<String, String> properties = new HashMap<>();
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.hbm2ddl.auto", "none");
//        properties.put("hibernate.format_sql", "true");

        EntityManagerFactory entityManagerFactory = new HibernatePersistenceProvider().createContainerEntityManagerFactory(new CustomPersistence(), properties);
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try{
            entityManager.getTransaction().begin();

            String jpql = """
                    SELECT s FROM Student s
                    """;
            EntityGraph<?> graph = entityManager.createEntityGraph(Student.class);
            graph.addAttributeNodes("enrollments");

            entityManager.createQuery(jpql, Student.class)
                    .setHint("jakarta.persistence.loadgraph", graph)
                            .getResultList()
                                    .forEach(a -> System.out.println(a.getEnrollments()));

//            TypedQuery<Student> q = entityManager.createQuery(jpql, Student.class);
//
//            q.getResultList().forEach(s -> System.out.println(s.getEnrollments()));
//
//            String jpql = """
//                    SELECT NEW org.jh.dto.EnrolledStudent(s, e) FROM Student s  JOIN s.enrollments e
//                    """;
//
//            TypedQuery<EnrolledStudent> q = entityManager.createQuery(jpql, EnrolledStudent.class);
//            q.getResultList().forEach(enrolledStudent -> System.out.println(enrolledStudent.student() + " ... " + enrolledStudent.enrollment()));

            entityManager.getTransaction().commit();
        }finally {
            entityManager.close();
        }
    }
}

//            String jpql = "SELECT p FROM Product p WHERE p.price > :price AND p.name LIKE :name";
//            q.setParameter("price", 10);
//            q.setParameter("name", "%a%");
//            List<Product> productList = q.getResultList();
//            TypedQuery<Product> q = entityManager.createQuery(jpql, Product.class);
//            List<Product> productList = q.getResultList();
//            for(Product p : productList){
//                System.out.println(p);
//            }
//            String jpql = "SELECT AVG(p.price) FROM Product p";
//            TypedQuery<Double> q = entityManager.createQuery(jpql, Double.class);
//
//            Double avg = q.getSingleResult();
//            System.out.println(avg); 