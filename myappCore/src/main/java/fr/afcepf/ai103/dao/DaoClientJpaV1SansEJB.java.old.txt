package fr.afcepf.ai103.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import fr.afcepf.ai103.data.Client;
import fr.afcepf.ai103.data.Compte;

public class DaoClientJpaV1SansEJB implements IDaoClient {
	
	private EntityManager entityManager;
	
	private void initEntityManagerSansEjb() {
		//1. créer l'objet technique EntityManagerFactory de JPA 
		//en analysant le fichier META-INF/persistence.xml
		EntityManagerFactory entityManagerFactory =
					Persistence.createEntityManagerFactory("myappCore");
		//myappCore est un nom logique d'une partie de la 
		//configuration de META-INF/persistence.xml
				
		//2. créer le EntityManager via la factory
		this.entityManager = entityManagerFactory.createEntityManager();
	}
	
	public DaoClientJpaV1SansEJB(){
		initEntityManagerSansEjb();
	}

	public Client insererNouveauClient(Client c) {
		try {
			entityManager.getTransaction().begin();
				//en entrée  la partie c.numClient vaut null
				entityManager.persist(c);//INSERT INTO SQL avec auto_increment
			entityManager.getTransaction().commit();
			return c; //en retour c.numClient ne sera plus null
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}
	}

	public Client rechercherClientParNumero(Long numero) {
		//SELECT FROM ... WHERE numero=?
		return entityManager.find(Client.class, numero);
	}

	public List<Client> rechercherClients() {
		return entityManager.createQuery("SELECT c FROM Client c",Client.class)
				            .getResultList();
	}

	public void mettreAjourClient(Client p) {
		try {
			entityManager.getTransaction().begin();
				entityManager.merge(p); //UDPATE SQL
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}
	}

	public void supprimerClient(Long numero) {
		 try {
			 entityManager.getTransaction().begin();
				 Client c= entityManager.find(Client.class, numero);
				 entityManager.remove(c); //DELETE SQL
			 entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public List<Compte> comptesPourClient(Long numClient) {
		// TODO Auto-generated method stub
		return null;
	}

}
