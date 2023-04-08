> Projet Android à créer et à livrer avant la date limite (31/03/2023 20h) via Moodle.
> Le simulateur utilisé doit être API 22 au minimum.
> Préciser le modèle du simulateur ainsi que l'image utilisée (ARM/INTEL).
> 25pts disponibles, noté sur 20

## Description du projet :
- [ ] La description succincte du projet doit être incluse.
- [ ] L'objectif général du projet doit être clair.

## Spécificités de l'IDE utilisé :
- [ ] Indiquer les spécificités de l'IDE utilisé (Android Studio, par exemple) s'il y en a.
	- [ ] Android studio
	- [ ] Rien de spécial de mon coté

## Éléments obligatoirement présents :
- [ ] 4 activités avec des liens entre elles ; au moins une avec retour de valeur (à préciser).
	- [ ] FromActivity (appelé par EstimationsActivity, retourne l'estimation calculé par les infos du formulaire).
	- [ ] EstimationActivity, affiche les informations sur une estiation donné ou lance le formulaire pour en créer une si aucune n'es fournie
	- [ ] CompareActivity, permet de comparer deux activitées en entrée
	- [ ] HistoricActivity, permet de visionner les estimations calculées.
- [ ] 1 Toast utilisé minimum.
- [x] Layout Paysage/Portrait différents et appropriés pour au moins 1 activité
	- [x] Pour l'activité comparaison.
- [x] 2 langues (si changement de la langue du téléphone, changement de la langue dans l’application).
- [ ] Liste d’items avec personnalisation (adapter personnalisé).
	- [ ] Historic, ça y est mais le visuel est moche.

- [x] 1 menu avec au moins un item dans la barre d’action et un item minimum dans le menu surgissant.
	- [x] Nom de l'apli, bouton quitter
	- [x] surgissant ( Acceuil, nouvelle estimation, historique, comparaison)
- [x] Stockage d’informations dans une BDD SQLite (au moins 1 table avec au minimum un accès en lecture et un autre en écriture).
	- [x] écriture à chaque nouvelle estimation
	- [x] lecture pentant le chargement de l'historique

- [x] 1 champ de saisie texte minimum.
	- [x] distance
	- [x] pois
- [x] 1 groupe de radio button minimum (2 radio minimum).
	- [x] Choix du transport (Avion, Bateau, camion, train).
- [x] 1 bouton minimum.
	- [x] Menu (3), validation From.

## Bonus 5pts :
- [ ] Appel à une API (publique ou maison).
- [ ] Ces points ne sont pas automatiques si le reste n’est pas bien fait ou que partiellement.

## Éléments spécifiques pour l'installation/l'exécution de l'application :
Si on utiise l'API il faut dire ou mettre une clé, pour l'instant c'est la mienne, mais si j'ai plus de crédits, il faut la changer.
