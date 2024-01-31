package com.g2.t5;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.g2.Model.ClassUT;
import com.g2.Model.Game;
import com.g2.Model.Player;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.Comparator;

import javax.crypto.SecretKey;


import com.g2.t5.MyData; //aggiunto

@CrossOrigin
@Controller
public class GuiController {

    
    
    private RestTemplate restTemplate;
    private String nameAuth; //Aggiunta A9
    private String IdAuth;  //Aggiunta A9
    public GuiController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<String> getLevels(String className) {
        List<String> result = new ArrayList<String>();
     
        int i;
        for(i = 1; i < 11; i++) {
            try {
                restTemplate.getForEntity("http://t4-g18-app-1:3000/robots?testClassId=" + className + "&type=randoop&difficulty="+String.valueOf(i), Object.class);
            } catch (Exception e) {
                break;
            }

            result.add(String.valueOf(i));
        }

        for(int j = i; j-i+1 < i; j++){
            try { // aggiunto
                restTemplate.getForEntity("http://t4-g18-app-1:3000/robots?testClassId=" + className + "&type=evosuite&difficulty="+String.valueOf(j-i+1), Object.class);
            } catch (Exception e) {
                break;
            }

            result.add(String.valueOf(j));
        }

        return result;
    }
    @GetMapping("/main")
    public String mainPage(Model model, @CookieValue(required = false) String jwt) {
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<String, String>();
        formData.add("jwt", jwt);

        Boolean isAuthenticated = restTemplate.postForObject("http://t23-g1-app-1:8080/validateToken", formData, Boolean.class);
        if(isAuthenticated == null || !isAuthenticated) return "redirect:/login";
        return "main";
    }

    @GetMapping("/classifica")
    public String classificaPage(Model model, @CookieValue(required = false) String jwt){
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<String, String>();
        formData.add("jwt", jwt);

        Boolean isAuthenticated = restTemplate.postForObject("http://t23-g1-app-1:8080/validateToken", formData, Boolean.class);
        if(isAuthenticated == null || !isAuthenticated) return "redirect:/login";

       List<Map<String, Object>> classifica = restTemplate.getForObject("http://t4-g18-app-1:3000/players", List.class);
       classifica.sort(Comparator.comparingInt((Map<String,Object> giocatore) -> (Integer) giocatore.get("wins")).reversed());
        model.addAttribute("classifica", classifica);
         
        return "classifica";
    }
    @GetMapping("/all_robots")
    public String AllRobotsPage(Model model, @CookieValue(required = false) String jwt){
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<String, String>();
        formData.add("jwt", jwt);

        Boolean isAuthenticated = restTemplate.postForObject("http://t23-g1-app-1:8080/validateToken", formData, Boolean.class);

        if(isAuthenticated == null || !isAuthenticated) return "redirect:/login";

        List<ClassUT> classes = getClasses();

        Map<Integer, String> hashMap = new HashMap<>();
        Map<Integer, List<MyData>> robotList = new HashMap<>();
        //Map<Integer, List<String>> evosuiteLevel = new HashMap<>();

        for (int i = 0; i < classes.size(); i++) {
            String valore = classes.get(i).getName();

            List<String> levels = getLevels(valore);
            System.out.println(levels);

            List<String> evo = new ArrayList<>(); //aggiunto
            for(int j = 0; j<levels.size(); j++){ //aggiunto
                if(j>=levels.size()/2)
                    evo.add(j,levels.get(j-(levels.size()/2)));
                else{
                    evo.add(j,levels.get(j+(levels.size()/2)));
                }     
            }
            System.out.println(evo);

            List<MyData> struttura = new ArrayList<>();
            
            for(int j = 0; j<levels.size(); j++){
                MyData strutt = new MyData(levels.get(j),evo.get(j));
                struttura.add(j,strutt);
            }
            

            for(int j = 0; j<struttura.size(); j++)  
                System.out.println(struttura.get(j).getList1());
            hashMap.put(i, valore);
            robotList.put(i, struttura);
            //evosuiteLevel.put(i, evo);
        }

        model.addAttribute("hashMap", hashMap);

        // hashMap2 = com.g2.Interfaces.t8.RobotList();

        model.addAttribute("hashMap2", robotList);
        return "all_robots";
    }
    @GetMapping("/game_mode")
    public String GameModePage(Model model, @CookieValue(required = false) String jwt){
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<String, String>();
        formData.add("jwt", jwt);

        Boolean isAuthenticated = restTemplate.postForObject("http://t23-g1-app-1:8080/validateToken", formData, Boolean.class);


        if(isAuthenticated == null || !isAuthenticated) return "redirect:/login";
        return "game_mode";
    }
    @GetMapping("/storico")
    public String storicoPage(Model model, @CookieValue(required = false) String jwt){
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<String, String>();
        formData.add("jwt", jwt);

        Boolean isAuthenticated = restTemplate.postForObject("http://t23-g1-app-1:8080/validateToken", formData, Boolean.class);


        if(isAuthenticated == null || !isAuthenticated) return "redirect:/login";
        
        Integer IdTemp = restTemplate.postForObject("http://t23-g1-app-1:8080/IdToken", formData, Integer.class);
        if(IdTemp != null){
            IdAuth = IdTemp.toString();
            System.out.println("ID utente: " + IdAuth);
        }else{
            System.out.println("ID utente non ricevuto o errore nella richiesta");
        }

        model.addAttribute("IdAuth", IdAuth);
      

        return "storico";
    }
    public List<ClassUT> getClasses() {
        ResponseEntity<List<ClassUT>> responseEntity = restTemplate.exchange("http://manvsclass-controller-1:8080/home",
            HttpMethod.GET, null, new ParameterizedTypeReference<List<ClassUT>>() {
        });

        return responseEntity.getBody();
    }
   
    @GetMapping("/choose")
    public String GUIController(Model model, @CookieValue(required = false) String jwt) {
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<String, String>();
        formData.add("jwt", jwt);

        Boolean isAuthenticated = restTemplate.postForObject("http://t23-g1-app-1:8080/validateToken", formData, Boolean.class);

        if(isAuthenticated == null || !isAuthenticated) return "redirect:/login";
        
         nameAuth = restTemplate.postForObject("http://t23-g1-app-1:8080/nameToken", formData, String.class);

        
        
        
        // fileController.listFilesInFolder("/app/AUTName/AUTSourceCode");
        // int size = fileController.getClassSize();
 
        List<ClassUT> classes = getClasses();

        Map<Integer, String> hashMap = new HashMap<>();
        Map<Integer, List<MyData>> robotList = new HashMap<>();
        //Map<Integer, List<String>> evosuiteLevel = new HashMap<>();

        for (int i = 0; i < classes.size(); i++) {
            String valore = classes.get(i).getName();

            List<String> levels = getLevels(valore);
            System.out.println(levels);

            List<String> evo = new ArrayList<>(); //aggiunto
            for(int j = 0; j<levels.size(); j++){ //aggiunto
                if(j>=levels.size()/2)
                    evo.add(j,levels.get(j-(levels.size()/2)));
                else{
                    evo.add(j,levels.get(j+(levels.size()/2)));
                }     
            }
            System.out.println(evo);

            List<MyData> struttura = new ArrayList<>();
            
            for(int j = 0; j<levels.size(); j++){
                MyData strutt = new MyData(levels.get(j),evo.get(j));
                struttura.add(j,strutt);
            }
            

            for(int j = 0; j<struttura.size(); j++)  
                System.out.println(struttura.get(j).getList1());
            hashMap.put(i, valore);
            robotList.put(i, struttura);
            //evosuiteLevel.put(i, evo);
        }

        model.addAttribute("hashMap", hashMap);

        // hashMap2 = com.g2.Interfaces.t8.RobotList();

        model.addAttribute("hashMap2", robotList);

        //model.addAttribute("evRobot", evosuiteLevel); //aggiunto
        return "choose";
    }

    
    @GetMapping("/report")
    public String reportPage(Model model, @CookieValue(required = false) String jwt) {
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<String, String>();
        formData.add("jwt", jwt);

        Boolean isAuthenticated = restTemplate.postForObject("http://t23-g1-app-1:8080/validateToken", formData, Boolean.class);

        if(isAuthenticated == null || !isAuthenticated) return "redirect:/login";
        
        return "report";
    }

    @GetMapping("/report1")
    public String report1Page(Model model, @CookieValue(required = false) String jwt) {
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<String, String>();
        formData.add("jwt", jwt);

        Boolean isAuthenticated = restTemplate.postForObject("http://t23-g1-app-1:8080/validateToken", formData, Boolean.class);

        if(isAuthenticated == null || !isAuthenticated) return "redirect:/login";
        
        return "report1";
    }

    

    @PostMapping("/save-data")
    public ResponseEntity<String> saveGame(@RequestParam int playerId, @RequestParam String robot,
            @RequestParam String classe, @RequestParam String difficulty, HttpServletRequest request) {

                if(!request.getHeader("X-UserID").equals(String.valueOf(playerId))) return ResponseEntity.badRequest().body("Unauthorized");

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
                LocalTime oraCorrente = LocalTime.now();
                String oraFormattata = oraCorrente.format(formatter);
                
                
                

                GameDataWriter gameDataWriter = new GameDataWriter();
                System.out.println("nameAuth: " + nameAuth);
                // g.setGameId(gameDataWriter.getGameId());
                Game g = new Game(playerId, nameAuth, "descrizione", "nome", difficulty);
                // g.setPlayerId(pl);
                // g.setPlayerClass(classe);
                // g.setRobot(robot);
                g.setData_creazione(LocalDate.now());
                g.setOra_creazione(oraFormattata);
                g.setClasse(classe);
                // System.out.println(g.getUsername() + " " + g.getGameId());

                // globalID = g.getGameId();

                JSONObject ids = gameDataWriter.saveGame(g);

                if(ids == null) return ResponseEntity.badRequest().body("Bad Request");

                return ResponseEntity.ok(ids.toString());
    }

    

    @GetMapping("/editor")
    public String editorPage(Model model, @CookieValue(required = false) String jwt) {
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<String, String>();
        formData.add("jwt", jwt);

        Boolean isAuthenticated = restTemplate.postForObject("http://t23-g1-app-1:8080/validateToken", formData, Boolean.class);

        if(isAuthenticated == null || !isAuthenticated) return "redirect:/login";
        

        return "editor";
    }

}
