package fr.afcepf.ai103.web.verif;

import javax.enterprise.inject.Default;
import javax.inject.Named;

//@Named est ici pour demander au framework CDI d'instancier et utiliser cette classe
//et de s'en service aux niveau des @Inject

//NB: avec @Named , @ManagedBean  , @Component/Spring ,
//le nom par défaut est nomClasseJavaAvecMinusculeAuDebut

@Named //@Named est l'équivalent CDI/JEE6+ de @Component
@Default
public class VerificateurV1 implements IVerificateur {

	@Override
	public boolean isPasswordOk(Long numClient, String pwd) {
		if(pwd!=null && pwd.equals("pwd" + numClient))
			return true;
		else 
		   return false;
	}

}
