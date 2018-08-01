package fr.afcepf.ai103.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.Local;
import javax.ejb.Stateless;

import fr.afcepf.ai103.dao.IDaoClient;
import fr.afcepf.ai103.dao.IDaoCompte;
import fr.afcepf.ai103.data.Compte;
import fr.afcepf.ai103.data.Operation;
@Stateless
@Local
//avec transactions automatiques sur toutes les méthodes dont transferer()
public class ServiceCompte {
	 @EJB
	 private IDaoCompte daoCompte;
	 
	 @EJB
	 private IDaoClient daoClient;
	 
	 public List<Compte> comptesDuClient(Long numClient){
    	 return daoClient.comptesPourClient(numClient);
     }
	 
	 //NB: l'execution de la méthode transferer() 
	 //se fera automatiquement en mode transactionnel (tout ou rien)
     public void transferer(double montant,long numCptDeb,long numCptCred) {
    	 Compte cptCred = daoCompte.rechercherCompteParNumero(numCptCred);
    	 cptCred.setSolde(cptCred.getSolde() + montant);
    	 //pas absolument besoin d'appeler  daoCompte.mettreAjourCompte(cptCred);
    	 //car cptCred est ici à l'état persistant (pas détaché)
    	 Compte cptDeb = daoCompte.rechercherCompteParNumero(numCptDeb);
    	 if(cptDeb.getSolde() < montant)
    		 throw new EJBException("solde insuffisant sur le compte à débiter");
    	 /*else*/
    	 cptDeb.setSolde(cptDeb.getSolde() - montant);
    	//pas absolument besoin d'appeler  daoCompte.mettreAjourCompte(cptDeb);
    	//car cptDeb est ici à l'état persistant (pas détaché)
     }
	
     
     /*
     VARIANTE 1 exploitant le lien "@OneToMany"
     
     //lorsque cette méthode sera exécutée dans jboss, il faudra que jboss initialise un 
     //debut de transaction. Et pour ça le "entityManager" doit quelquefois être créé au même moment
     public List<Operation> operationsDuCompte(Long numCompte){
    	 Compte cpt = daoCompte.rechercherCompteParNumero(numCompte);
    	 //for(Operation op : cpt.getDernieresOperations()) { }
    	 int n = cpt.getDernieresOperations().size(); //temporaire pour eviter lazy exception
    	 //Soit via une boucle for , soit via un appel à .size() on provoque volontairement
    	 //une remontée immédiate des valeurs de la tables "operation" vers des objets
    	 //de la liste dernieresOperations avant qu'il ne soit trop tard pour le faire
    	 System.out.println("le compte " + numCompte + " a " + n + " operations");
    	 return cpt.getDernieresOperations();
     //à la fin de l'exécution de cette méthode jboss déclenche automatiquement commit 
     // tout va bien ou rollback si exception
     // et quelquefois le entityManager est fermé ici (si avait été crée dans le haut de cette méthode)
     }
     */
     
     // VARIANTE 2 s'appyant sur une requête spécifique du DAO:
     public List<Operation> operationsDuCompte(Long numCompte){
        return daoCompte.dernieresOperations(numCompte);
     }
     
     
     
     
}
