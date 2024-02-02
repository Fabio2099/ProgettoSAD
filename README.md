# A9 -- R9
Componenti:
- Davide Fabio: M63001454
- Giampetraglia Federica: M63001358
- Riccardi Francesco: M63001372
- De Nigris Fabio: M63001397

# RIASSUNTO MODIFICHE AL SOFTWARE
Questo documento fornisce una panoramica delle principali modifiche apportate al software originariamente disponibile al seguente link: [Testing-Game-SAD-2023/T11-G41](https://github.com/Testing-Game-SAD-2023/T11-G41). Gli aggiornamenti sono i seguenti:


**Task T2-3:**

- Modifiche al Controller per l'estrazione del nome e dell'ID dell'utente dal token JWT.
- Aggiunta di nuovi endpoint API per ottenere il nome e l'ID dell'utente.
- Aggiornamento della funzione di generazione del token JWT per includere il nome del giocatore.

**Task T4:**

- Ristrutturazione del database per supportare nuove funzionalità.
- Modifiche alla tabella "turns" per includere nuovi campi critici per la classifica e lo storico.
- Aggiunta di nuove colonne alla tabella "players" per riflettere informazioni dettagliate sulle prestazioni dei giocatori.
- Introduzione di nuove API per gestire l'interazione con i dati degli utenti e facilitare la creazione delle classifiche.
- Estensione della logica di business con nuove funzioni nel Service e aggiornamenti al DTO di Turn.
- Ottimizzazione del modello per popolare i campi aggiunti nella tabella "players".
- Aggiunta di endpoint API per recuperare e aggiornare informazioni sulle partite giocate e sui giocatori.

**Task T5:**

- Introduzione di diagrammi di contesto e ottimizzazione mediante diagramma delle classi.
- Modifiche al GUIController per una gestione efficace dell'autenticazione e la selezione dei robot.
- Modifiche al GameDataWriter per aggiungere il nome del giocatore alla tabella "player".
- Sviluppo di nuove pagine web per migliorare l'esperienza utente e l'interazione con l'applicativo.

**Task T6:**

- Miglioramenti al MyController con l'introduzione di nuove variabili per la gestione delle selezioni di classe, robot e difficoltà.
- Aggiornamenti mirati alla tabella "Turn" del database per registrare parametri di gioco aggiuntivi.
- Sviluppo di nuove funzionalità per l'interazione avanzata con i robot di gioco e la personalizzazione delle sfide.

Queste modifiche hanno contribuito a migliorare l'esperienza complessiva del gioco e ad aggiungere nuove funzionalità importanti come la visualizzazione dello storico delle partite, la creazione della classifica dei giocatori e l'ottimizzazione dell'interfaccia utente. Per ulteriori dettagli, si consiglia di consultare la documentazione completa dei singoli task e delle relative API.



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

