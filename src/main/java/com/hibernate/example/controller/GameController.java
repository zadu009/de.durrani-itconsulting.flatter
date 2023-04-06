package com.hibernate.example.controller;

import java.util.List;

import com.hibernate.example.Game;
import com.hibernate.example.GameEngine;
import com.hibernate.example.GameValue;
import com.hibernate.example.Member;
import com.hibernate.example.models.Engineuser;
import com.hibernate.example.models.Message;
import com.hibernate.example.models.PatientData;
import com.hibernate.example.repository.GameRepoUtils;
import com.hibernate.example.repository.MessageRepository;
import com.hibernate.example.utils.GameValueUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@Transactional
@RestController
@RequestMapping("/game")
public class GameController {
	
	@Autowired
	GameValueUtils gameValueUtils;
	
	@Autowired
	GameRepoUtils gameRepoUtils;



    @RequestMapping(value = "/id/{id}/data/{data}/password/{password}")
    @ResponseBody
    public Member postMember
            (@PathVariable Integer id, @PathVariable String data, @PathVariable String password ) {
        Member member = new Member();
        member.setId(id);
        member.setData(data);
        member.setPassword(password);
        return null;
    }

    @RequestMapping(value = "/getStatus/{id}")
    @ResponseBody
    public String postMember
            (@PathVariable Integer id) {
        Member member = new Member();
        member.setId(id);
        return null;
    }

    @PostMapping(value = "/updateStatus", consumes = "application/json", produces = "application/json")
    public ResponseEntity updateStatus(@RequestBody PatientData patientData)  {
        Member member = new Member();
        Integer id = patientData.getId();
        RestTemplate restTemplate = new RestTemplate();
        member.setId(id);
        member.setData(patientData.getData());
        try {
            restTemplate.postForLocation("https://kaskovi.de:8081/notify/"+id.toString(), member.getFcmtoken());
            return ResponseEntity.ok("successfully updated TestPatient for " + member.getId());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("could not create TestPatient");
        }
    }

    @PostMapping(value = "/updatefcmtoken", consumes = "application/json", produces = "application/json")
    public ResponseEntity updatefcmtoken(@RequestBody PatientData patientData) {
        Member member = new Member();
        member.setId(patientData.getId());
        member.setFcmtoken(patientData.getFcmtoken());
        try {
            return ResponseEntity.ok("successfully ufcmtoken updated TestPatient for " + member.getId());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("could not create TestPatient");
        }
    }

    @PostMapping(value = "/register", consumes = "application/json", produces = "application/json")
    public ResponseEntity createPatientData(@RequestBody PatientData patientData){
        Member member = new Member();
        member.setPassword(patientData.getHashedPassword());
        member.setData(patientData.getData());

        try {
            return ResponseEntity.ok("successfully created TestPatient for "+ member.getId());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("could not create TestPatient");
        }
    }
    
    @PostMapping(value = "/getValue", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public GameValue getGameValue (@RequestBody Game game) {
        return gameValueUtils.getGameValue(game);
    }
    
    @PostMapping(value = "/getValue", consumes = "application/x-www-form-urlencoded", produces = "application/json")
    @ResponseBody
    public GameValue getGameValueApp (Game game) {
        return gameValueUtils.getGameValue(game);
    }
    
    @PostMapping(value = "/checkIfUserExists", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public GameEngine checkIfUserExists (@RequestBody GameEngine gameEngine) {
        return gameRepoUtils.checkIfUserExist(gameEngine);
    }
    
    @PostMapping(value = "/getAllUsers", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public List<Engineuser> getAllUsers (@RequestBody GameEngine gameEngine) {
        return gameRepoUtils.findAll();
    }
    
    @PostMapping(value = "/checkSignIn", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public GameEngine checkSignIn (@RequestBody GameEngine gameEngine) {
        return gameRepoUtils.checkSignIn(gameEngine);
    }
    
    @PostMapping(value = "/saveEngineuser", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public Engineuser saveEngineuser(@RequestBody GameEngine gameEngine) {
        return gameRepoUtils.saveEngineuser(gameEngine);
    }
        
    @PostMapping(value = "/saveGames", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public GameEngine saveGames(@RequestBody GameEngine gameEngine) {
    	gameRepoUtils.deleteGames(gameEngine);
        return gameRepoUtils.saveGames(gameEngine);
    }
    
    @PostMapping(value = "/deleteGames", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public GameEngine deleteGames(@RequestBody GameEngine gameEngine) {
        return gameRepoUtils.deleteGames(gameEngine);
    }
    
    @PostMapping(value = "/getGames", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public GameEngine getGames(@RequestBody GameEngine gameEngine) {
        return gameRepoUtils.getGames(gameEngine);
    }
    
    @PostMapping(value = "/saveMessage", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public GameEngine saveMessage(@RequestBody GameEngine gameEngine) {
        return gameRepoUtils.saveMessage(gameEngine);
    }
    
    
    
}
