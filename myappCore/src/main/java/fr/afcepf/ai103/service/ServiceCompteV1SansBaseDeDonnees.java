package fr.afcepf.ai103.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.afcepf.ai103.data.Compte;
import fr.afcepf.ai103.data.Operation;

public class ServiceCompteV1SansBaseDeDonnees {
	 private Map<Long,Compte> mapComptes = new HashMap<Long,Compte>();//simulation en mémoire
	 
	 public ServiceCompteV1SansBaseDeDonnees() {
		 Compte cpt1 = new Compte();    	 cpt1.setNumero(123456L);
    	 cpt1.setLabel("compte courant");    cpt1.setSolde(500.0);
    	 mapComptes.put(cpt1.getNumero(), cpt1);
    	 
    	 Compte cpt2 = new Compte();    	          cpt2.setNumero(97855245L);
    	 cpt2.setLabel("compte épargne (livret A)");  cpt2.setSolde(400.0);
    	 mapComptes.put(cpt2.getNumero(), cpt2);
	 }
	 
     public void transferer(double montant,long numCptDeb,long numCptCred) {
    	 Compte cptDeb = mapComptes.get(numCptDeb);
    	 cptDeb.setSolde(cptDeb.getSolde() - montant);
    	 Compte cptCred = mapComptes.get(numCptCred);
    	 cptCred.setSolde(cptCred.getSolde() + montant);
     }
	
     public List<Compte> comptesDuClient(Long numClient){
    	 List<Compte> listeComptes = new ArrayList<Compte>();
    	 //simulation de valeurs récupérées en base:
    	 listeComptes.add(mapComptes.get(123456L));
    	 listeComptes.add(mapComptes.get(97855245L));
    	 return listeComptes;
     }
     
     public List<Operation> operationsDuCompte(Long numCompte){
    	 List<Operation> listeOperations = new ArrayList<Operation>();
    	 //simulation de valeurs récupérées en base:
    	 if(numCompte != null && (numCompte % 2) == 0) {
	    	 listeOperations.add(new Operation(1L,new Date(),-30.0, "achat livres"));
	    	 listeOperations.add(new Operation(2L,new Date(),-10.0, "achat dvd"));
    	 }else {
    		 listeOperations.add(new Operation(3L,new Date(),-35.0, "achat vetement"));
	    	 listeOperations.add(new Operation(4L,new Date(),-18.0, "achat crème solaire"));
    	 }
    	 return listeOperations;
     }
     
     
}
