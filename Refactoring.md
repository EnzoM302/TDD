# Refactoring du projet Mastermind

---

## 1. Classe `Reponse`

### Refactorings

| Changement | Technique | Raison                                                                                                                                                                                                                                  |
|---|---|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| L'attribut `position` est supprimé : il devient une variable locale de la boucle et un paramètre de `evaluationCaractere` et `estPlace` | *Replace Field with Parameter* | `position` était un état partagé, modifié par `compare` et lu par `estPlace`. Les méthodes privées dépendaient d'un état caché, ce qui les rendait difficiles à tester et à raisonner. Elles ne dépendent plus que de leurs paramètres. |
| `if / else if / else` remplacé par des *guard clauses* dans `evaluationCaractere` | *Guard clauses* | Les `else` après un `return` sont superflus. Le code est plus plat et se lit de haut en bas.                                                                                                                                            |
| `contains(String.valueOf(c))` remplacé par `indexOf(c) >= 0` | Nettoyage de style | J'ai vu que je pouvais évité de créer une `String` intermédiaire en utilisant indexOf donc je me suis dit que ça pouvais faire gagner en performance comme on ne crée plus de String.                                                   |

---

## 2. Classe `Partie`

### Refactorings

| Changement | Raison |
|---|---|
| Garde en début de méthode (`if (...) throw`) puis corps à plat | Moins d'imbrication : le cas d'erreur est traité d'abord, le cas normal ensuite. |
| Une seule condition pour la fin de partie au lieu de deux `if` appelant `done()` | Les deux `if` exprimaient une seule règle : la partie est terminée si le mot est trouvé **ou** s'il n'y a plus d'essais. |
| Suppression de la variable `motSecret` intermédiaire dans `tourDeJeu` | Elle n'était utilisée qu'une fois. |

### Corrections de comportement

- **`done()` n'était jamais appelé.** Le test `if (isTerminee())` lit l'attribut `partieTerminee`, qui ne passe à `true` que dans `done()`. La condition était donc toujours fausse. La bonne condition est `reponse.lettresToutesPlacees() || nbEssais >= NB_ESSAIS_MAX`.
- Faute dans le message d'exception corrigée (« dépassé », « essais »).

## 3. Classe `Mastermind`

### Refactorings

| Changement | Raison |
|---|---|
| `evaluation` : `isEmpty()` puis deux `.get()` remplacés par `orElseThrow(...)` | Exploite correctement `Optional`, supprime la variable `partieEnCours` et les `.get()` répétés. |
| Suppression de `isJeuEnCours` | Méthode privée jamais appelée (code mort). |

---
