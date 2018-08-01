package fr.afcepf.ai103.test;

import fr.afcepf.ai103.dao.DaoClientJpa;
import fr.afcepf.ai103.dao.IDaoClient;
import fr.afcepf.ai103.data.Client;

public class TestApp {

	public static void main(String[] args) {
		IDaoClient daoClient =  new DaoClientJpa();
		for(Client c :daoClient.rechercherClients()) {
			System.out.println(c.toString());
		}
		Client cc = new Client();
		cc.setNom("toto");
		daoClient.insererNouveauClient(cc);
		System.out.println("nouveau numClient" + cc.getNumClient());
		//...
		System.exit(0);//pour forcer l'arrêt (pour compenser entityManager.close() )
	}
	
	/* NB: via un double click sur h2...jar (situé dans 
	C:\Users\formation\.m2\repository\com\h2database\h2\1.4.197)
	on peut lancer une console préfini de H2 permettant de visualiser le contenu des tables
	Attention :
	    * URL = jdbc:h2:~/bank_db (plutot que jdbc:h2:~/test)
	    * bien se déconnecter pour ne pas bloquer d'autres programmes (ex: appli java)
	*/
}
