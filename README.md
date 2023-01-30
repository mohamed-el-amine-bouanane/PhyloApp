# Fusion des projets AR1C et AR2C

# Présentation  

Ce projet consiste en la fusion de deux projet complémentaire le premier AR2C qui a pour objectif de creer une matrice de distance à partir de valeurs representatives assignés à chaque espèce, le 2éme AR1C a comme but de réaliser une comparaison de sequences d'ADN afin de donner des valeurs indicatives sur le rapprochement ou éloignement des sequences génétiques.
En fusionnant nos 2 projet, on a reussi à réaliser un projet capable de traiter toutes les informations depuis la récuperation des sequences ADN (correspondante aux espèces qui sont stockées sous forme de Base de Données) depuis un fichier texte, le calcul de leurs distance et l'intégration de cette information dans une matrice de distance.

## Sujet detaillés
[Sujet AR1C](22_01_AR1C.pdf)
[Sujet AR2C](22_01_AR2C.pdf)

## Prérequis
- maven 3.6.3 installé.
- java 8 ou 11.

## Comment installer maven
https://maven.apache.org/install.html

## Verification de la configuration maven
```bash
mvn --version
Apache Maven 3.6.3
Maven home: /usr/share/maven
Java version: 11.0.14, vendor: Ubuntu, runtime: /usr/lib/jvm/java-11-openjdk-amd64
Default locale: fr_FR, platform encoding: UTF-8
OS name: "linux", version: "5.13.0-37-generic", arch: "amd64", family: "unix"
```
Verifier bien que Maven utilise Java 11 ou 8

## Compilation
Aller au repertoire du projet.
```bash
mvn compile
```

## Exécution

Execution avec maven  :
Aller au repertoire du projet.
```bash
mvn -X exec:java
``` 

