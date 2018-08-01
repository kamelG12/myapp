package fr.afcepf.ai103.dao;

import java.util.List;

import fr.afcepf.ai103.data.Compte;
import fr.afcepf.ai103.data.Operation;

/*
 * DAO = Data Access Object 
 * avec méthodes CRUD : Create , Retreive , Update , Delete
 * avec throws RuntimeException (ou sous classe JpaException) implicites
 */
public interface IDaoCompte {
	public Compte insererNouveauCompte(Compte p);
	
	public Compte rechercherCompteParNumero(Long numero);
    public List<Compte> rechercherComptes();
    //... autres recherches ...
    
    public void mettreAjourCompte(Compte p);
    public void supprimerCompte(Long numero);

	public List<Operation> dernieresOperations(Long numCompte);
}
