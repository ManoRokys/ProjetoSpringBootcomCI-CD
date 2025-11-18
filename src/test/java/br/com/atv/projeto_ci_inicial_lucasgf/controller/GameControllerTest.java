package br.com.atv.projeto_ci_inicial_lucasgf.controller;

import br.com.atv.projeto_ci_inicial_lucasgf.model.Game;
import br.com.atv.projeto_ci_inicial_lucasgf.service.GameService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(GameController.class)
class GameControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GameService gameService;

    @Test
    void getAllGames_shouldReturnListOfGames() throws Exception {
        Game game1 = new Game(1L, "Game1", "Action", 2020);
        Game game2 = new Game(2L, "Game2", "RPG", 2021);
        when(gameService.getAllGames()).thenReturn(Arrays.asList(game1, game2));

        mockMvc.perform(get("/games"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value("Game1"))
                .andExpect(jsonPath("$[1].name").value("Game2"));
    }

    @Test
    void getGameById_shouldReturnGame_whenExists() throws Exception {
        Game game = new Game(1L, "Game1", "Action", 2020);
        when(gameService.getGameById(1L)).thenReturn(Optional.of(game));

        mockMvc.perform(get("/games/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Game1"));
    }

    @Test
    void getGameById_shouldReturnNotFound_whenNotExists() throws Exception {
        when(gameService.getGameById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/games/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void addGame_shouldReturnCreatedGame() throws Exception {
        Game game = new Game(null, "New Game", "Adventure", 2022);
        Game savedGame = new Game(1L, "New Game", "Adventure", 2022);
        when(gameService.addGame(game)).thenReturn(savedGame);

        mockMvc.perform(post("/games")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"New Game\",\"genre\":\"Adventure\",\"releaseYear\":2022}"))
                .andExpect(status().isOk());
    }
}