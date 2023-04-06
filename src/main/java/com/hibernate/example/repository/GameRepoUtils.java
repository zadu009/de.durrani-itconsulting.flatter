package com.hibernate.example.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hibernate.example.Game;
import com.hibernate.example.GameEngine;
import com.hibernate.example.models.Engineuser;
import com.hibernate.example.models.Message;

@Component
public class GameRepoUtils {

	@Autowired
	GameEngineRepository repo;

	@Autowired
	MessageRepository messageRepo;
	
	@Autowired
	GameRepository gameRepository;
	
	public GameEngine checkIfUserExist(GameEngine gameEngine) {
		boolean exist = repo.existsByEmailOrNickname(gameEngine.getEmail(),gameEngine.getNickname());
		gameEngine.setUserExistStatus(String.valueOf(exist));
		return gameEngine;
	}
	
	public GameEngine checkSignIn(GameEngine gameEngine) {
		boolean exist = repo.existsByEmailAndPassword(gameEngine.getEmail(),gameEngine.getPassword());
		gameEngine.setSignInStatus(String.valueOf(exist));
		return gameEngine;
	}
	
	public List<Engineuser> findAll() {
		return repo.findAll();

	}
	
	public Engineuser saveEngineuser(GameEngine gameEngine) {
		Engineuser user = new Engineuser();
		user.setEmail(gameEngine.getEmail());
		user.setNickname(gameEngine.getNickname());
		user.setPassword(gameEngine.getPassword());
		return repo.saveAndFlush(user);
	}
		
	public GameEngine saveGames(GameEngine gameEngine) {
		for(Game game: gameEngine.getGamesList()) {
			com.hibernate.example.models.Game videogame = new com.hibernate.example.models.Game();
			videogame.setEmail(gameEngine.getEmail());
			videogame.setAverageprice(game.getAveragePrice());
			videogame.setName(game.getName());
			videogame.setPlatform(game.getPlatform());
			gameRepository.saveAndFlush(videogame);
		}
		return gameEngine;
	}
	
	public GameEngine getGames(GameEngine gameEngine) {
		List<com.hibernate.example.models.Game> gameList = gameRepository.findAllByEmail(gameEngine.getEmail());
		List<Game>gameDbList = new ArrayList<Game>();
		for(com.hibernate.example.models.Game game : gameList) {
			Game gameDb = new Game();
			gameDb.setAveragePrice(game.getAverageprice());
			gameDb.setName(game.getName());
			gameDb.setPlatform(game.getPlatform());
			gameDbList.add(gameDb);
			gameEngine.setGamesList(gameDbList);
		}
		return gameEngine;
	}
	
	public GameEngine deleteGames(GameEngine gameEngine) {
		gameRepository.removeByEmail(gameEngine.getEmail());
		return gameEngine;
	}
	
	public GameEngine saveMessage(GameEngine gameEngine) {
		Message message = new Message();
		message.setEmail(gameEngine.getEmail());
		message.setFirstName(gameEngine.getFirstname());
		message.setLastName(gameEngine.getLastname());
		message.setMessage(gameEngine.getMessage());
		messageRepo.saveAndFlush(message);
		return gameEngine;
	}
	
}
