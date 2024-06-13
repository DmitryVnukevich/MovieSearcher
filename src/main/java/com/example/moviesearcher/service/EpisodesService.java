package com.example.moviesearcher.service;

import com.example.moviesearcher.entities.Episodes;
import com.example.moviesearcher.repository.EpisodesRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EpisodesService {

    private final EpisodesRepository episodesRepository;

    public EpisodesService(EpisodesRepository episodesRepository) {
        this.episodesRepository = episodesRepository;
    }

    @Transactional
    public Episodes saveEpisode(Episodes episode) {
        return episodesRepository.save(episode);
    }

    public List<Episodes> findAllEpisodes() {
        return episodesRepository.findAll();
    }

    public Episodes findEpisodeById(Long id) {
        return episodesRepository.findById(id).orElse(null);
    }

    @Transactional
    public void deleteEpisode(Long id) {
        episodesRepository.deleteById(id);
    }

    @Transactional
    public Episodes updateEpisode(Episodes episode) {
        return episodesRepository.save(episode);
    }
}
