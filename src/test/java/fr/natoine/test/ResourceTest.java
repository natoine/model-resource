package fr.natoine.test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import fr.natoine.model_resource.Resource;
import fr.natoine.model_resource.URI;
import fr.natoine.model_resource.UriStatus;

import junit.framework.TestCase;

public class ResourceTest  extends TestCase
{
	public ResourceTest(String name) 
	{
	    super(name);
	}
	
	public void testCreate()
	{
		Resource _testResource = new Resource();
		_testResource.setContextCreation("Resource Test");
		_testResource.setCreation(new Date());
		_testResource.setLabel("test resource");
		URI _representsResource = new URI();
		_representsResource.setEffectiveURI("http://www.testURI.fr");
		_testResource.setRepresentsResource(_representsResource);
		
		Collection<URI> uris = new ArrayList<URI>() ;
		URI _testURI1 = new URI();
		_testURI1.setEffectiveURI("http://www.testURI1.fr");
		uris.add(_testURI1);
		URI _testURI2 = new URI();
		_testURI2.setEffectiveURI("http://www.testURI2.fr");
		uris.add(_testURI2);
		_testResource.setUris(uris);
		
		Collection<UriStatus> _status = new ArrayList<UriStatus>() ;
		UriStatus _status1 = new UriStatus();
		_status1.setLabel("test père status");
		_status.add(_status1);
		UriStatus _status2 = new UriStatus();
		_status2.setLabel("test fils status");
		_status2.setFather(_status1);
		_status.add(_status2);
		_testResource.setUrisStatus(_status);
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("resource");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try{
        	tx.begin();
        	em.persist(_testResource);
        	tx.commit();
        }
        catch(Exception e)
        {
        	 System.out.println( "[ResourceTest] unable to persist" );
        	 System.out.println(e.getStackTrace());
        }
        em.close();
	}
	
	public void testRetrieve()
	{
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("resource");
        EntityManager newEm = emf.createEntityManager();
        EntityTransaction newTx = newEm.getTransaction();
        newTx.begin();
        List resources = newEm.createQuery("from Resource").getResultList();
        System.out.println( resources.size() + " resource(s) found" );
        Resource loadedResource ;
        for (Object u : resources) 
        {
        	loadedResource = (Resource) u;
            System.out.println("[ResourceTest] Resource id : " + loadedResource.getId()  
            		+ " contextCreation : " + loadedResource.getContextCreation()
            		+ " label : " + loadedResource.getLabel()
            		+ " date : " + loadedResource.getCreation()
            		);
        }
        newTx.commit();
        newEm.close();
        // Shutting down the application
        emf.close();
	}
}
