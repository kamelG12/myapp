package fr.afcepf.ai103.service;

import java.util.List;

import fr.afcepf.ai103.data.Client;

public interface IServiceClient {

	Client rechercherInfosClient(Long numClient);

	List<Client> rechercherListeClientsParNom(String nom);

	Client saveOrUpdateClient(Client cli);

	List<Client> rechercherTousLesClients();

}