package fr.afcepf.ai103.dao;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.afcepf.ai103.data.Compte;
import fr.afcepf.ai103.data.Operation;

@Stateless
@Local
//@TransactionManagement(TransactionManagementType.CONTAINER) par defaut
//@TransactionAttribute(TransactionAttributeType.REQUIRED) par defaut
public class DaoCompteJpa implements IDaoCompte {
	
	//myappCore est un nom logique d'une partie de la 
	//configuration de META-INF/persistence.xml
	//@PersistenceContext initialise entityManager en fonction de persistence.xml dans 
	//un projet EJB (ou Spring)
	@PersistenceContext(unitName="myappCore")
	private EntityManager entityManager;
	
	
	public DaoCompteJpa(){
		
	}

	//commit/rollback declenché automatiquement par container EJB selon exception
	public Compte insererNouveauCompte(Compte c) {
			entityManager.persist(c);//INSERT INTO SQL avec auto_increment
			return c; //en retour c.numCompte ne sera plus null
	}

	public Compte rechercherCompteParNumero(Long numero) {
		//SELECT FROM ... WHERE numero=?
		return entityManager.find(Compte.class, numero);
	}

	public List<Compte> rechercherComptes() {
		return entityManager.createQuery("SELECT c FROM Compte c",Compte.class)
				            .getResultList();
	}

	public void mettreAjourCompte(Compte p) {
				entityManager.merge(p); //UDPATE SQL
	}

	public void supprimerCompte(Long numero) {
				 Compte c= entityManager.find(Compte.class, numero);
				 entityManager.remove(c); //DELETE SQL
	}

	@Override
	public List<Operation> dernieresOperations(Long numCompte) {
		return entityManager.createNamedQuery("Operation.findByNumCompte",Operation.class)
				            .setParameter("numCpt", numCompte)
				            .getResultList();
	}

}
