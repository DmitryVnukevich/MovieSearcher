package com.example.moviesearcher.controller;

import com.example.moviesearcher.entities.Episodes;
import com.example.moviesearcher.service.EpisodesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/episodes")
public class EpisodesController {

    private final EpisodesService episodesService;

    @Autowired
    public EpisodesController(EpisodesService episodesService) {
        this.episodesService = episodesService;
    }

    @PostMapping
    public Episodes createEpisode(@RequestBody Episodes episode) {
        return episodesService.saveEpisode(episode);
    }

    @GetMapping
    public List<Episodes> getAllEpisodes() {
        return episodesService.findAllEpisodes();
    }

    @GetMapping("/{id}")
    public Episodes getEpisodeById(@PathVariable Long id) {
        return episodesService.findEpisodeById(id);
    }

    @PutMapping("/{id}")
    public Episodes updateEpisode(@PathVariable Long id, @RequestBody Episodes episodeDetails) {
        episodeDetails.setId(id);
        return episodesService.updateEpisode(episodeDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteEpisode(@PathVariable Long id) {
        episodesService.deleteEpisode(id);
    }
}
