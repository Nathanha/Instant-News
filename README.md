# Instant-News

Application d'actualité affichant toutes les dernières informations provenant de différents journaux français.

Projet développé dans le cadre d'un exercice ayant pour but de démontrer mes compétences en développement Kotlin suivant l'architecture MVVM.<br>

## Installation

1) Avoir Android Studio installé sur sa machine, ainsi que les extensions nécessaires pour lancer une application sur un téléphone ou un émulateur.

2) Cloner le projet à l'aide de la commande : ``git clone https://github.com/Nathanha/Instant-News.git``.

3) Ouvrir le projet avec Android Studio.

4) Une fois le chargement terminé, lancer le projet sur un téléphone ou un émulateur.

## Choix réalisés

* [Glide](https://github.com/bumptech/glide) : Je l'ai utilisé pour charger et mettre en cache les images affichées dans l'application. Deux autres alternatives étaient utilisables : 
    - [Picasso](https://square.github.io/picasso/) : Dans l'ensemble moins performant que Glide.
    - [Fresco](https://frescolib.org/) : Plus performant que Glide, mais plus compliqué à utiliser et donc moins adapté à un petit projet.
* [Hilt](https://developer.android.com/training/dependency-injection/hilt-android) : Permets de faire de l'injection de dépendances. Très utilisé et bien documenté sur la documentation officielle d'Android, c'est pour cela que je l'ai choisi. De plus, je n'ai pas trouvé de vrai équivalent en Kotlin.
* [Retrofit2](https://square.github.io/retrofit/) : J'ai choisi cette librairie HTTP plutôt que Volley par exemple, pour sa facilité de connexion avec Google GSON qui permet de parser une réponse HTTP en JSON dans des classes Kotlin.
* [Coroutines](https://kotlinlang.org/docs/coroutines-overview.html) : Librairie de référence en Kotlin pour faire de la programmation asynchrone. Étant adaptée à Kotlin, il était préférable de l'utiliser plutôt que RxJava par exemple.
* [Junit](https://junit.org/junit5/) : Librairie installée par défaut lors du démarrage d'un nouveau projet. Une des plus utilisées en Java, elle est très documentée. De plus, j'ai eu l'occasion de l'utiliser dans de nombreux projets Java à l'école.
* [Mockito](https://github.com/mockito/mockito-kotlin) : Permets de mocker (simuler) le fonctionnement d'une classe. C'est un outil très pratique et simple à utiliser. Je l'ai choisi car je l'ai utilisé durant plusieurs projets en Java à l'école. J'ai utilisé la variante pour Kotlin, car elle était plus adaptée à ce projet.

## Problèmes non traités

* Affichage d'un warning provenant de la librairie Retrofit2 lors du lancement des tests.

## Axes d'amélioration

### Projet 
* Revoir le design des deux pages pour suivre les standards MaterialDesign et améliorer l'expérience utilisateur.
* Ajout d'une pagination sur la liste des actualités pour optimiser le chargement. Le but serait de charger les 30 premières actualités par exemple, puis de charger les 30 suivantes lorsque l'on arrive en bas de la liste.
* Ajout d'un Swipe-to-Refresh sur la liste pour la recharger.
* Ajouter une base de données locale à l'application pour stocker les actualités chargées. Cela permettrait l'accès à ces dernières sans connexion à Internet.
* Ne pas recharger les activités lors de la rotation du téléphone.
* Supprimer la fin du contenu d'un article qui contient le nombre de caractères restants non visibles (exemple: [+1000 chars]).

### Organisation

* Utiliser une stratégie Git comme Git Flow par exemple pour découper le travail de chaque fonctionnalité dans des branches séparées. La taille de ce projet étant mineure, la mise en place de ce type d'organisation ne m'a pas semblé nécessaire.

### Tests

* Seulement des tests unitaires ont été écrits dans ce projet. Ils ont pour but de tester le bon fonctionnement de l'architecture MVVM et de certaines méthodes utilisées dans les vues. La mise en place future de tests d'interface permettrait de s'assurer que les scénarios d'utilisation se déroulent parfaitement et qu'aucun problème n'est présent durant l'affichage des données sur les vues.

## Temps de réalisation du projet

En dehors de la rédaction de ce document, j'ai passé approximativement <strong>5 h 30</strong> pour développer l'application et les tests.<br>
En plus de ce temps de réalisation, j'ai passé un peu de temps pour faire des recherches principalement sur l'architecture MVVM qui ne m'était pas familière.

## Auteur

**Nathan HARO** _alias_ [@Nathanha](https://github.com/Nathanha) et [@nathanApiwork](https://github.com/nathanApiwork)
