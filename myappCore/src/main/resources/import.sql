//import.sql est un script SQL automatiquement déclenché par hibernate 
//au démarrage de appli (ou test) en mode hibernate.hbm2ddl.auto=create  

INSERT INTO Client(numClient,prenom,nom,email,adresse,telephone,password) VALUES (1,'alex','Therieur','alex.therieur@gmail.com','1 rue elle 75000 Paris','0102030405' , 'pwd1')
INSERT INTO Client(numClient,prenom,nom,email,adresse,telephone,password) VALUES (2,'axelle','Aire','axelle.aire@gmail.com','1 rue lionceau 69000 Lyon','0504030201' , 'pwd2')
INSERT INTO Client(numClient,prenom,nom,email,adresse,telephone,password) VALUES (3,'alain','Therieur','alain.therieur@gmail.com','2 rue elle 75000 Paris','0102030405' , 'pwd3')

INSERT INTO Compte(numCompte,label,solde) VALUES (1,'compte courant 1', 500.1) 
INSERT INTO Compte(numCompte,label,solde) VALUES (2,'compte epargne 2', 300.6) 
INSERT INTO Compte(numCompte,label,solde) VALUES (3,'compte courant 3', 200.1) 
INSERT INTO Compte(numCompte,label,solde) VALUES (4,'compte epargne 4', 800.6) 

INSERT INTO Client_Compte(client_id,compte_id) VALUES (1,1) 
INSERT INTO Client_Compte(client_id,compte_id) VALUES (1,2)
INSERT INTO Client_Compte(client_id,compte_id) VALUES (2,3) 
INSERT INTO Client_Compte(client_id,compte_id) VALUES (2,4) 

INSERT INTO Operation(numero,dateOp,montant,label,numCpt) VALUES (1,'2018-07-17', -15.5 , 'achat 1' , 1 )
INSERT INTO Operation(numero,dateOp,montant,label,numCpt) VALUES (2,'2018-07-7', -5.5 , 'achat 2' , 1 )
INSERT INTO Operation(numero,dateOp,montant,label,numCpt) VALUES (3,'2018-07-17', -8.5 , 'achat 3' , 2 )
INSERT INTO Operation(numero,dateOp,montant,label,numCpt) VALUES (4,'2018-07-12', -4.5 , 'achat 4' , 2 )