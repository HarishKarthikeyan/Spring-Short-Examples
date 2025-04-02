package org.jh;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.jh.entities.Product;
import org.jh.entities.Student;
import org.jh.entities.keys.StudentKey;
import org.jh.persistence.CustomPersistenceUnit;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    static Logger logger = Logger.getLogger(Main.class.getName());
    public static void main(String[] args) {
        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.format_sql", "true");
        properties.put("hibernate.hbm2ddl.auto", "update");

//        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa-hibernate-mysql");
        EntityManagerFactory entityManagerFactory = new HibernatePersistenceProvider()
                .createContainerEntityManagerFactory(new CustomPersistenceUnit(), properties);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try{
            entityManager.getTransaction().begin();

            var id = new StudentKey();
            id.setId(1L);
            id.setCode("code");



//            var s = new Student();
//            s.setId(id);
//            s.setName("Harish");
//            entityManager.persist(s);

            var s = entityManager.find(Student.class, id);
            logger.log(Level.INFO, "{0}", s);
            entityManager.getTransaction().commit();
        }finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}