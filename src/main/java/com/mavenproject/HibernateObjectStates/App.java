package com.mavenproject.HibernateObjectStates;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Configuration con = new Configuration().configure().addAnnotatedClass(Laptop.class);
        SessionFactory sf = con.buildSessionFactory();
        Session session = sf.openSession();
        
        session.beginTransaction();
        
        Laptop laptop = new Laptop();//new State
//        laptop.setLid(78);
//        laptop.setBrand("Lenovo");
//        laptop.setPrice(650);
        //transient state
        
        session.save(laptop);//persistent state, whatever the changes done in the state will be reflected in the database
        
        session.getTransaction().commit();
        
        session.detach(laptop);//in detached state, doen't reflect changes made after this statement
        laptop.setPrice(400);
        
        System.out.println(session.get(Laptop.class, 78)); //get would give the object instantly after calling the method
        
        //proxy object is given
        session.load(Laptop.class, 78); // doen't run the query but if the result object is used somewhere then the query is run
        
        
        
    }
}
