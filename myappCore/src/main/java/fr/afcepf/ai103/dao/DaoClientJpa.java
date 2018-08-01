package fr.afcepf.ai103.dao;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.afcepf.ai103.data.Client;
import fr.afcepf.ai103.data.Compte;

//@Stateless
@Singleton //depuis la version 3.1 des EJB (comme @Stateless mais 
           // avec la garantie d'avoir une seule instance de la classe d'EJB 
           // fabriquée par le serveur JEE)
@Local
//@TransactionManagement(TransactionManagementType.CONTAINER) par defaut
//@TransactionAttribute(TransactionAttributeType.REQUIRED) par defaut
public class DaoClientJpa implements IDaoClient {
	
	//myappCore est un nom logique d'une partie de la 
	//configuration de META-INF/persistence.xml
	//@PersistenceContext initialise entityManager en fonction de persistence.xml dans 
	//un projet EJB (ou Spring)
	@PersistenceContext(unitName="myappCore")
	private EntityManager entityManager;
	
	
	public DaoClientJpa(){
		
	}
	
	

	//commit/rollback declenché automatiquement par container EJB selon exception
	public Client insererNouveauClient(Client c) {
			entityManager.persist(c);//INSERT INTO SQL avec auto_increment
			return c; //en retour c.numClient ne sera plus null
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
				entityManager.merge(p); //UDPATE SQL
	}

	public void supprimerClient(Long numero) {
				 Client c= entityManager.find(Client.class, numero);
				 entityManager.remove(c); //DELETE SQL
	}
	
	@Override
	public List<Compte> comptesPourClient(Long numClient) {
		return entityManager.createNamedQuery("Client.comptesDuClient", Compte.class)
				            .setParameter("numClient", numClient)
				            .getResultList();
	}

	@Override
	public List<Client> rechercherClientsParNom(String nom) {
		return entityManager.createNamedQuery("Client.parNom", Client.class)
	            .setParameter("nom", nom)
	            .getResultList();
	}

	

}
