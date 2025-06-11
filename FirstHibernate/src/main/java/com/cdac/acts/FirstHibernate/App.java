package com.cdac.acts.FirstHibernate;

import java.util.Scanner;

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
    	Configuration hibernateConfiguration = null;
    	SessionFactory hibernateFactory = null;
    	Session hibernateSession = null;
    	try
    	{
    	hibernateConfiguration = new Configuration();
    	hibernateConfiguration.configure("hibernate.cfg.xml");
    	hibernateFactory = hibernateConfiguration.buildSessionFactory();
    	hibernateSession = hibernateFactory.openSession();
    	
    	try 
    	(Scanner sc = new Scanner(System.in))
    			{
    		System.out.println("Enter username");
    		String userName = sc.next();
    		System.out.println("Enter the password");
    		String password = sc.next();
    		System.out.println("Enter the name");
    		String name = sc.next();
    		System.out.println("Enter the email");
    		String email = sc.next();
    		
    		users objUser = new users(userName,password,name,email);
    		
    		hibernateSession.beginTransaction();
    		hibernateSession.persist(objUser);
    		hibernateSession.getTransaction().commit();
    		
    		System.out.println("User Registered");
    			}catch(Exception e) 
    			{
    				e.printStackTrace();
    			}
    		
    			}
    	
    	finally {
    		if(hibernateSession!=null)
    			hibernateSession.close();
    		if(hibernateFactory!=null)
    			hibernateFactory.close();
    	}

    }
}

