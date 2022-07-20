# Bao Loc 

Projet de démo de résolution du problème mathématique dit de "Bao Loc".

## Prérequis

* Environnement Linux (certaines commandes/fonctionnalités ne fonctionneront pas sous un autre OS)
* Java 17
* Node V14 ou supérieur
* (Optionnel) Installation de maven >= 3.6.x ou utiliser le wrapper présent à la racine du serveur `services-quali-cible`
  via la commande locale `./mvnw`

## Démarrer le backend

Aller ds le répertoire racine du serveur **./baoloc** et exécuter la commande :
`./mvnw spring-boot:run` 
ou en utilisant un client maven compatible déjà installé localement: 
`mvn spring-boot:run`

Pour vérifier que le serveur est bien démarré, ouvrir l'url suivante :
http://localhost:8080

Une version du frontend doit déjà être directement accessible (déployée dans le répertoire **./src/main/resources/static**)
http://localhost:8080/index.html

## Redéployer le frontend localement dans ./src/main/resources/static

Si des modifications ont été apportées ou que le frontend déployé n'est pas à jour,
il faut se placer à la racine du répertoire **./baoloc-angular-frontend**
Ensuite, "builder" l'application Angular :
`npm run build`
Puis copier les fichiers générés ds **src/main/resources/static**
`npm run deploy:locally`

## Démarrer le frontend sur Node

Se placer dans le répertoire **./baoloc-angular-frontend**
Pour un premier démarrage il faut tout d'abord installer les librairies nécessaires via la commande : 
`npm ci` or `npm i`
Ensuite, pour lancer le front en mode "dev", exécuter la commande :
`ng serve`

Le client est accessible à l'url :
http://localhost:4200