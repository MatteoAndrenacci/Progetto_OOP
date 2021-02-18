# Progetto_OOP
Con tale progetto ci si propone di realizzare, tramite framework SpringBoot, un Web Service che permetta ad un Client di effettuare operazioni REST API del tipo GET o POST.
L'applicazione andrà a studiare gli eventi che avranno luogo in Canada, sfruttando le API del sito ticketmaster. 
## Start
E' possibile avviare l'applicazione direttamente dal nostro IDE, importando tale repository e mandando in run tramite 'Run As: SpringBoot App'. A questo punto l'applicazione è in ascolto
alla porta http://localhost:8080/

## Modello 
Ogni evento è descritto da diverse proprietà:
- Nome dell'evento
- Genere dell'evento
- Data dell'evento
- Luogo dell'evento
- Città in cui si terrà l'evento
- Stato in cui si terrà l'evento
- Paese in cui si terrà l'evento

## Richieste
Il client ha la possibilità di inviare diversi tipi di richieste:
- Ottenere la lista di tutti gli eventi in programmazione
- Ottenere una lista di eventi filtrati in base a parametri scelti
- Ottenere delle statistiche sul numero di eventi in ogni stato
- Ottenere delle statistiche sul numero di eventi in ogni stato, raggruppati per genere
- ottenere delle statistiche riguardo al numero massimo, minimo e medio di eventi mensili.
