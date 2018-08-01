package fr.afcepf.ai103.data;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@NamedQuery(name="Operation.findByNumCompte" ,
            query= "SELECT o FROM Operation o WHERE o.compte.numero = :numCpt")
public class Operation {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long numero;
	
	@Temporal(TemporalType.DATE) //seule la date est significative pas l'heure
	@Column(name="dateOp")
    private Date date;
	
    private Double montant;
    
    private String label;
    
    @ManyToOne() //Many Operation To One Compte
    @JoinColumn(name="numCpt"  ) //nom de la clef etrangère dans table Operation
    private Compte compte; //avec get/set mais pas dans toString()
    
   
	public Operation() {
		super();
	}
	
	public Operation(Long numero, Date date, Double montant, String label) {
		super();
		this.numero = numero;
		this.date = date;
		this.montant = montant;
		this.label = label;
	}


	public Long getNumero() {
		return numero;
	}
	public void setNumero(Long numero) {
		this.numero = numero;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Double getMontant() {
		return montant;
	}
	public void setMontant(Double montant) {
		this.montant = montant;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}

	public Compte getCompte() {
		return compte;
	}

	public void setCompte(Compte compte) {
		this.compte = compte;
	}
    
	
    
}
