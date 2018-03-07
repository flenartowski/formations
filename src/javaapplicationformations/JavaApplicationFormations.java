/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplicationformations;

import entities.Calendrier;
import entities.Formation;
import entities.Inscription;
import entities.Session;
import entities.Specialite;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author francoise
 */
public class JavaApplicationFormations {
    private static  EntityManagerFactory emf,emf2;
    private static EntityManager em,em2;
    private static final String PERSISTENCE_UNIT_NAME = "JavaApplicationFormationsPU";
    private static final String PERSISTENCE_UNIT_NAME_2 = "JavaApplicationFormationsPU2";
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         
   
        emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        em = emf.createEntityManager();
        emf2 = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME_2);
        em2= emf2.createEntityManager();
        Specialite specialite=new Specialite("réseaux");
        Specialite specialite2=new Specialite("programmation oreintée objets");
        Formation formation =new Formation("tcp/ip",3,10,specialite);
        Session session= new Session(10,formation);
        Inscription inscription=new Inscription(session);
         inscription.setDateInscription(new Date());
         List<Calendrier>calendrier=new ArrayList();
         Calendrier jour1 =new Calendrier(new Date());
        // Calendrier jour2 =new Calendrier(new Date("2018-03-02"));
        calendrier.add(jour1);
        //calendrier.add(jour2);
        session.setCalendriers(calendrier);
        em.getTransaction().begin();
        em.persist(specialite);
        em.persist(specialite2);
        em.persist(formation);
        em.persist(jour1);
        //em.persist(jour2);
        em.persist(session);
        em.persist(inscription);
        em.getTransaction().commit();
       
         List<Specialite>specialites= em2.createQuery("select s from Specialite s").getResultList();
        for(Specialite sp:specialites)
        {
            System.out.println("Liste "+sp.getNomSpecialite());
           
        }
        List<Formation>formations= em2.createQuery("select f from Formation f").getResultList();
        for(Formation f:formations)
        {
            System.out.println(f.getSpecialite().getNomSpecialite()+ " " + f.getTitre());
           
        }
}
}
    

