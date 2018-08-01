package fr.afcepf.ai103.data;

import java.util.List;

import javax.persistence.Column;
/* javax.persistence = package de JPA */
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity //entité persistante prise en charge par JPA/Hibernate
//@Table(name="Client")
@NamedQueries({
  @NamedQuery(name="Client.comptesDuClient", 
            query="SELECT cpt FROM Client cli INNER JOIN cli.comptes cpt WHERE cli.numClient = :numClient"
            ),
  @NamedQuery(name="Client.parNom", 
            query="SELECT cli FROM Client cli  WHERE cli.nom = :nom")
})
//pas besoin de ON ....=.... apres INNER JOIN cli.comptes cpt 
//car JPA/Hibernate analyse toutes les structures java et connait les valeurs du ON ...=....
// cpt est un alias pour un élément de cli.comptes
public class Client {
	@Id //identifiant (primary key)
	@GeneratedValue(strategy=GenerationType.IDENTITY) //IDENTITY convient le mieux
	                                                  //pour les bases de données récentes
	//@Column(name="numClient")
	private Long numClient;

    @ManyToMany( fetch = FetchType.LAZY)
    @JoinTable(name = "Client_Compte",
    		    joinColumns = {@JoinColumn(name = "client_id")},
    			inverseJoinColumns = {@JoinColumn(name = "compte_id")})
    @JsonIgnore //pour que les comptes problématiques en mode LAZY
                //ne soient pas pris en compte lors de la conversion JAVA vers JSON
                //déclenchée par la technologie jackson elle même utilisée par JAX-RS
    private List<Compte> comptes; //+get/set
	
	private String nom; 
	private String prenom; 
	private String email; 
	private String adresse; 
	
	@Column(length=15) //VARCHAR(15)
	private String telephone;
	
    private String password;
    
   
    
    
    //+get/set , +toString() , + default constructor
    
    
    @Override
	public String toString() {
		return "Client [numClient=" + numClient + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email
				+ ", adresse=" + adresse + ", telephone=" + telephone + ", password=" + password + "]";
	}
    
	public Client() {
		super();
	}

	public Long getNumClient() {
		return numClient;
	}
	
	public void setNumClient(Long numClient) {
		this.numClient = numClient;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public List<Compte> getComptes() {
		return comptes;
	}

	public void setComptes(List<Compte> comptes) {
		this.comptes = comptes;
	}
    
   
	
	
}
