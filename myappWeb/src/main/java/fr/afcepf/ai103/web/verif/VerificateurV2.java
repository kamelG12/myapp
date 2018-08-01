package fr.afcepf.ai103.web.verif;

import javax.enterprise.inject.Alternative;
import javax.inject.Named;

@Named //@Named est l'équivalent CDI/JEE6+ de @Component 
//@Any par defaut un peu comme @Named
//@Secondaire (annotation personnalisée avec meta-annotation @Qualifier )
@Alternative
public class VerificateurV2 implements IVerificateur {

	@Override
	public boolean isPasswordOk(Long numClient, String pwd) {
		if(pwd!=null && pwd.equals("pwd_" + numClient))
			return true;
		else 
		   return false;
	}

}
