                                                 1 : PROJET TEST – COMMUNICATION ASYNCHRONE ENTRE MICROSERVICES
                                           1.1 Présentation

Ce projet est une mise en œuvre d’une architecture microservices basée sur Spring Boot, illustrant une communication asynchrone entre deux services :

User-Service

Compte-Service

La communication entre ces deux services est réalisée via Apache Kafka, permettant un échange d’événements de manière découplée, scalable et tolérant aux pannes.

1.2 Architecture du Système

Le système est composé des éléments suivants :

Kafka-Config → Serveur de configuration centralisé basé sur Spring Cloud Config

Eureka-Service → Service de registre avec Netflix Eureka

Gateway-Service → Passerelle API basée sur Spring Cloud Gateway

User-Service → Consommateur d’événements Kafka

Compte-Service →Producteur d’événements Kafka

PostgreSQL → Base de données relationnelle avec PostgreSQL

Docker → Conteneurisation via Docker

         Schéma d’Architecture
                           +-------------------+
                           |      Client       |
                           +---------+---------+
                                     |
                                     v
                          +----------------------+
                          |   Gateway-Service    |
                          |       (Port 8080)    |
                          +----------+-----------+
                                     |
                                     v
                          +----------------------+
                          |    Eureka-Service    |
                          |       (Port 8761)    |
                          +----------+-----------+
                                     |
            -------------------------------------------------
            |                                               |
            v                                              v
    +------------------+                          +------------------+
    |   User-Service   | -----> Kafka ----->       | Compte-Service  |
    |   (Consumer)     |                          |   (CProducer)     |
    +------------------+                          +------------------+
            |                                               |
            v                                               v
        PostgreSQL                                     PostgreSQL

                    +--------------------------------+
                    |        Kafka-Config            |
                    |  (Configuration centralisée)   |
                    +--------------------------------+
1.3 Communication Asynchrone

Le fonctionnement est le suivant :

Le Compte-Service publie des événements dans Kafka.

Le User-Service consomme ces événements.

Les services ne dépendent pas directement l’un de l’autre.

Cette approche permet :

                  - Un meilleur découplage

                  -Une meilleure scalabilité

                  -Une meilleure tolérance aux pannes

                  -Une architecture plus robuste

                                                2 : OBJECTIF DU PROJET

Ce projet a pour objectif de :

Mettre en avant l’importance de Docker dans le domaine du développement logiciel.

Illustrer des méthodes pratiques d’utilisation de Docker.

Comprendre le fonctionnement d’une architecture microservices distribuée.

Implémenter une communication asynchrone entre services.

Mettre en place un environnement complet et conteneurisé prêt au déploiement.

                                             3 : CONTENEURISATION AVEC DOCKER

Le projet comporte 5 services principaux, et pour chacun :

                        A Un Dockerfile est présent dans chaque servive.

Le Dockerfile permet :

De générer le fichier .jar

D’exposer le port du service

De construire une image Docker propre à chaque microservice

D’assurer une isolation des environnements

                        B: DOCKER COMPOSE

Un fichier docker-compose.yml est inclus dans le projet.

Il permet :

De démarrer tous les services en une seule commande

De définir l’ordre de démarrage des services

De gérer le réseau entre les conteneurs

De simplifier l’environnement de développement

D’orchestrer Kafka, PostgreSQL et les microservices

         Commande de lancement
docker-compose up --build
               3 : TECHNOLOGIES UTILISÉES

Java 17

Spring Boot

Spring Cloud

Apache Kafka

Docker

Maven

PostgreSQL
