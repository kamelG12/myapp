package fr.afcepf.ai103.data;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Compte {
	@Id //identifiant (primary key)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="numCompte")
	private Long numero;
	
	private String label;
	private Double solde;
	
	//One Compte To Many Operation
	@OneToMany(mappedBy="compte", fetch=FetchType.LAZY) //mappedBy="nomJava_RelationInverse_ManyToOne"
	private List<Operation> dernieresOperations;//avec get/set mais pas dans toString()
	
	
	public Compte() {
		super();
	}
	
	public Long getNumero() {
		return numero;
	}
	public void setNumero(Long numero) {
		this.numero = numero;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public Double getSolde() {
		return solde;
	}
	public void setSolde(Double solde) {
		this.solde = solde;
	}

	public List<Operation> getDernieresOperations() {
		return dernieresOperations;
	}

	public void setDernieresOperations(List<Operation> dernieresOperations) {
		this.dernieresOperations = dernieresOperations;
	}

	
	
}
