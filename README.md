# Jeu DorfRomantik 

Ce jeu est développé par Vincent Teissier, David Akagunduz et Bamba Top.

## Principes généraux :

le joueur doit assembler des tuiles hexagonales pour former un paysage harmonieux.
Une partie se déroule de la façon suivante. La première tuile est posée automatiquement. À chaque tour, une tuile est révélée au joueur, et celui-ci choisit la position et l'orientation dans laquelle poser cette tuile. La seule contrainte est que la nouvelle tuile doit être adjacente à une tuile déjà posée.
La partie se termine lorsque 50 tuiles ont été posées. On calcule alors le score du joueur en additionnant les points obtenus pour chaque poche de terrain. Si deux tuiles sont connectées par des côtés qui montrent le même terrain, ces tuiles appartiennent à la même poche. Le nombre de tuiles dans une poche, élévé au carré, donne le nombre de points accordés.

## Lancement du programme :

Ce projet Java utilise un `Makefile` situé dans le dossier `src/main/java` pour automatiser la compilation, l'exécution, la création d'une archive JAR, ainsi que le nettoyage des fichiers temporaires.

## Prérequis
- Assurez-vous d'avoir `make`, `javac`, et `java` installés sur votre système.
### ⚠️ **Naviguez dans le répertoire `src/main/java` avant d'utiliser les commandes suivantes.**

### Compilation :

```bash
make
```

### Lancement du programme 

Utiliser la commande suivante pour lancer le programme qui permet de crée une grille :

```bash
make run
```


### Pour effacer les fichiers `.class`

Utiliser la commande pour supprimer les fichiers class :

```bash
make clean
```

```bash
  make Main.jar
- **Executer le JAR**  
```
  ```bash
  java -jar Main.jar
  ```

  
  ## Rapport d'avancement :
  
  Pour une analyse détaillée du projet, veuillez consulter le rapport d'avancement `Rapport.pdf`,disponible dans ce dépôt. Ce document inclut des descriptions de fonctionnalités, des diagrammes de structure, et des réflexions personnelles des auteurs sur le développement du programme.

  ## API officielle de Java :

- Documentation : [API Officielle Java]((Java SE 21 &amp; JDK 21)](https://iut-fbleau.fr/docs/java/api/index.html))
- Auteur : Oracle

### Crédits :

- Code :
  
  - Bamba Top (@topb)
  - Vicent Teissier(@teissier)
  - David Akagunduz (@Akagunduz)


