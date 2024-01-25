//variabili per la selezione della classe e del robot
var classe = null;
var robot = null;
var difficulty = null;
//variabili per il login
var user = null;
var password = null;
var classe =  null;

// Variabile per tenere traccia del bottone precedentemente selezionato
var bottonePrecedente1 = null;
// Variabile per tenere traccia del bottone precedentemente selezionato
var bottonePrecedente2 = null;

var selectedElement = null;

const getCookie = (name) => {
  const value = `; ${document.cookie}`;
  const parts = value.split(`; ${name}=`);
  if (parts.length === 2) return parts.pop().split(';').shift();
  return null;
}

/*const parseJwt = (token) => {
  try {
      return JSON.parse(atob(token.split('.')[1]));
  } catch (e) {
      return null;
  }
};

document.addEventListener("DOMContentLoaded", (e) => {
  document.getElementById("usernameField").innerText = parseJwt(getCookie("jwt")).sub;
});*/

const parseJwt = (token) => {
  try {
      return JSON.parse(atob(token.split('.')[1]));
  } catch (e) {
      return null;
  }
};

document.addEventListener("DOMContentLoaded", (e) => {
  const jwtData = parseJwt(getCookie("jwt"));

  if(jwtData){
    const usernameField = document.getElementById("usernameField");
    usernameField.innerText = jwtData.sub; //getEmail()

    const nameField = document.getElementById("nameField");
    if (jwtData.name){
      nameField.innerText = jwtData.name;
    }else {
      nameField.innerText = "Nome non disponibile";
    }
  }
});
function Handlebuttonclass1(id, button){
  $(document).ready(function () {
    classe = id;
    console.log('Hai cliccato sul bottone delle classi con id: ' + classe);
    document.querySelectorAll("span.levels:not(.hidden)").forEach((el) => el.classList.add("hidden"));
   /* if(document.getElementById("levels-"+button.id).classList.contains("hidden")) document.getElementById("levels-"+button.id).classList.remove("hidden");
    // Se il bottone precedentemente selezionato è diverso da null
    // allora rimuoviamo la classe highlighted
    if (bottonePrecedente1 != null) {
      bottonePrecedente1.classList.remove("highlighted");
    }
    if (button.classList.contains("highlighted")) {
      button.classList.remove("highlighted");
    } else {
      button.classList.add("highlighted");
    }

    bottonePrecedente1 = button;   */
});
}

function Handlebuttonclass(id, button) {
  $(document).ready(function () {
    classe = id;
    console.log('Hai cliccato sul bottone delle classi con id: ' + classe);
    document.querySelectorAll("span.levels:not(.hidden)").forEach((el) => el.classList.add("hidden"));
    if(document.getElementById("levels-"+button.id).classList.contains("hidden")) document.getElementById("levels-"+button.id).classList.remove("hidden");
    // Se il bottone precedentemente selezionato è diverso da null
    // allora rimuoviamo la classe highlighted
    if (bottonePrecedente1 != null) {
      bottonePrecedente1.classList.remove("highlighted");
    }
    if (button.classList.contains("highlighted")) {
      button.classList.remove("highlighted");
    } else {
      button.classList.add("highlighted");
    }

    bottonePrecedente1 = button;
  });
}

function Handlebuttonrobot(id, button, rob, size) { //modificato
  $(document).ready(function () {
    robot = rob;
    if(robot == "evosuite"){ // aggiunto
      difficulty = parseInt(id)-parseInt(size)/2; // devo prendere l'id attuale meno la metà della grandezza totale del vettore di robot
      difficulty = difficulty.toString();
    }
    else{ // aggiunto
      difficulty = id;
    }
    //difficulty = id;
    console.log('Hai cliccato sul bottone del robot con id: ' + robot);

    // Se il bottone precedentemente selezionato è diverso da null
    // allora rimuoviamo la classe highlighted
    if (bottonePrecedente2 != null) {
      bottonePrecedente2.classList.remove("highlighted");
    }

    if (button.classList.contains("highlighted")) {
      button.classList.remove("highlighted");
    } else {
      button.classList.add("highlighted");
    }
    bottonePrecedente2 = button;

  });
}

function redirectToPagereport() {
  console.log(classe);
  console.log(robot);
  console.log(difficulty);
  if (classe && robot && difficulty) {

    // $.ajax({
    //   url: 'http://localhost:8082/sendVariable', // L'URL del tuo endpoint sul server
    //   type: 'POST', // Metodo HTTP da utilizzare
    //   data: {
    //     myVariable: classe,
    //     myVariable2: robot
    //   }, // Dati da inviare al server
    //   success: function (response) {
    //     console.log('Dati inviati con successo');
    //     alert("Dati inviati con successo");
    //     // Gestisci la risposta del server qui
    //     window.location.href = "/report";
    //   },
    //   error: function (error) {
    //     console.error('Errore nell invio dei dati');
    //     alert("Dati non inviati con successo");
    //     // Gestisci l'errore qui
    //   }
    // });
    localStorage.setItem("classe", classe);
    localStorage.setItem("robot", robot);
    localStorage.setItem("difficulty", difficulty);
    window.location.href = "/report";
  }
  else {
    alert("Seleziona una classe e un robot");
    console.log("Seleziona una classe e un robot");
  }

}

function redirectToPageReport1(){
  console.log(classe);
  if(classe){
    localStorage.setItem("classe", classe);
    localStorage.setItem("robot", "Tutti i Robot");
    window.location.href = "/report";
  }
  else {
    alert("Seleziona una classe");
    console.log("Seleziona una classe");
  }
}

function redirectToPagemain() {
  window.location.href = "/main";
}
function redirectToAllRobots() {
  window.location.href = "/all_robots";
}
function redirectToGameMode() {
  window.location.href = "/game_mode";
}
function redirectToPageChoose(){
	window.location.href = "/choose";
	}
function redirectToClassifica(){
	window.location.href = "/classifica";
	}
function redirectToStorico(){
	window.location.href = "/storico";
	}

// function redirectToPagemainlogin() {

//   user = document.getElementById("username").value;
//   password = document.getElementById("password").value;
// if(user && password ){
//   alert("Login effettuato con successo");
  
//   $.ajax({
//     url:'http://localhost:8082/login-variabiles',
//     type: 'POST',
//     data: { 
//       var1: user, 
//       var2: password
//     },

//   })


//   window.location.href = "/main";
// }
// else{
//   alert("Inserisci username e password");
// }
// }

function redirectToPageeditor() {
  $.ajax({
    url:'http://localhost/api/save-data',
    data: {
      playerId: parseJwt(getCookie("jwt")).userId,
      classe: classe,
      robot: robot,
      difficulty: difficulty
    },
    type:'POST',
    success: function (response) {
      // Gestisci la risposta del server qui
      localStorage.setItem("gameId", response.game_id);
      localStorage.setItem("turnId", response.turn_id);
      localStorage.setItem("roundId", response.round_id);
      window.location.href = "/editor";
    },
    dataType: "json",
    error: function (error) {
      console.error('Errore nell invio dei dati');
      alert("Dati non inviati con successo");
      // Gestisci l'errore qui
    }
  })
}

// Funzione per gestire il click sul bottone di download
function downloadFile() {
  fileId = classe;
  if (fileId) {
    const downloadUrl = 'http://localhost/api/downloadFile/' + fileId;

    fetch(downloadUrl, {
      method: 'GET',
    })
      .then(function(response) {
        if (response.ok) {
          return response.blob();
        } else {
          throw new Error('Errore nella risposta del server');
        }
      })
      .then(function(blob) {
        const url = window.URL.createObjectURL(blob);
        const link = document.createElement('a');
        link.href = url;
        link.download = "class.java";
        link.click();
        window.URL.revokeObjectURL(url);
      })
      .catch(function(error) {
        console.error('Errore nel download del file', error);
      });
  } else {
    console.log('Nessun file selezionato');
  }
}

function redirectToLogin() {
  if(confirm("Sei sicuro di voler effettuare il logout?")){
    fetch('http://localhost/logout', {
        method: 'GET',
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Richiesta logout non andata a buon fine');
        }
        else{
          console.log("stai per essere reindirizzato alla pagina di login");
          window.location.href = "/login";
        }
    })
    .catch((error) => {
      console.error('Error:', error);
    });
  }
}

function saveLoginData() {
  var username = document.getElementById("username").value;

  localStorage.setItem("username", username);
}
