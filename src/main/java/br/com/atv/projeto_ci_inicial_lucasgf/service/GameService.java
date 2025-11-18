package br.com.atv.projeto_ci_inicial_lucasgf.service;

import br.com.atv.projeto_ci_inicial_lucasgf.model.Game;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class GameService {
    private final List<Game> games = new ArrayList<>();
    private final AtomicLong counter = new AtomicLong(1);

    public List<Game> getAllGames() {
        return new ArrayList<>(games);
    }

    public Optional<Game> getGameById(Long id) {
        return games.stream().filter(game -> game.getId().equals(id)).findFirst();
    }

    public Game addGame(Game game) {
        game.setId(counter.getAndIncrement());
        games.add(game);
        return game;
    }
}