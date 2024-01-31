# A9 -- R9
Componenti:
- Davide Fabio: M63001454
- Giampetraglia Federica: M63001358
- Riccardi Francesco: M63001372
- De Nigris Fabio: M63001397

# RIASSUNTO MODIFICHE AL SOFTWARE
Questo documento fornisce una panoramica delle principali modifiche apportate al software originariamente disponibile al seguente link: [Testing-Game-SAD-2023/T11-G41](https://github.com/Testing-Game-SAD-2023/T11-G41). Gli aggiornamenti sono i seguenti:

## T2-3:
- Sono state realizzate modifiche per rendere disponibili agli altri task i campi relativi al nome e all'ID del giocatore.

## T4:
- Aggiornamento significativo del database con modifiche alle tabelle `turn` e `player`.
- Introduzione di un endpoint API per la tabella `player` per la generazione di classifiche e dati storici.

## T5:
- Aggiunta di nuove pagine, tra cui una schermata iniziale di selezione delle opzioni che permette al giocatore di scegliere tra nuova partita, visualizzazione dello storico e della classifica.
- Implementata l'interfaccia per la nuova modalità di gioco '1vTutti'.
- Implementazione delle modifiche necessarie per la visualizzazione dello storico delle partite e delle classifiche (compresa l'aggiunta delle nuove pagine).

## T6:
- Modifiche apportate per rendere disponibili per la classifica e lo storico i campi relativi alla difficoltà della partita, al robot sfidato e alla classe scelta.

Per una comprensione dettagliata dei dettagli implementativi e dell'uso, fare riferimento alla [documentazione del progetto](https://github.com/Testing-Game-SAD-2023/T11-G41/blob/main/DOCUMENTATION.md).





# GUIDA ALL'INSTALLAZIONE

## PASSO 1
Scaricare Docker Desktop per il proprio sistema operativo.

## PASSO 2
Si deve avviare lo script "installer.bat" se si sta usando una distribuzione Windows oppure "installermac.sh" nel caso si utilizzi macOS o una distro di Linux.
Per MacOS - eseguire nella cartella dove è presente il file ”installermac.sh” il comando "chmod +x installermac.sh" per renderlo eseguibile, e poi "./installermac.sh" per eseguirlo.
Tali script dovranno essere avviati unicamnete con Docker in esecuzione, altrimenti l'installazione non partirà. Saranno effettuate le seguenti operazioni:
1) Creazione della rete "global-network" comune a tutti i container.
2) Creazione del volume "VolumeT9" comune ai Task 1 e 9 e del volume "VolumeT8" comune ai Task 1 e 8.
3) Creazione dei singoli container in Docker desktop.

NOTA: il container relativo al Task 9 ("Progetto-SAD-G19-master") si sospenderà autonomamente dopo l'avvio. Esso viene utilizzato solo per "popolare" il volume "VolumeT9" condiviso con il Task 1.

## PASSO 3
Si deve configurare il container "manvsclass-mongo_db-1" così come descritto anche nella documentazione del Task 1.
Per fare ciò bisogna fare le seguenti operazioni:
1) Posizionarsi all'interno del terminale del container
2) Digitare il comando "mongosh"
3) Digitare i seguenti comandi:

        use manvsclass
        db.createCollection("ClassUT");
        db.createCollection("interaction");
        db.createCollection("Admin");
        db.createCollection("Operation");
        db.ClassUT.createIndex({ difficulty: 1 })
        db.Interaction.createIndex({ name: "text", type: 1 })
        db.interaction.createIndex({ name: "text" })
        db.Admin.createIndex({username: 1})

L'intera applicazione è adesso pienamente configurata e raggiungibile sulla porta :80. Per una guida all'installazione e all'utilizzo più completa consultare la documentazione al capitolo 8.

# VIDEO DIMOSTRAZIONE
## Admin



https://github.com/Testing-Game-SAD-2023/T11-G41/assets/128593973/e4e1ef4d-f2f3-42b2-8be4-ef9caa219b93



## Player



https://github.com/Testing-Game-SAD-2023/T11-G41/assets/128593973/c94398b2-178e-4b7f-a6d6-685c2689c03c


La presente documentazione d'installazione è frutto del lavoro del T11-G41, poichè non è stata fatta nessuna modifica che impatti l'installazione del software.

