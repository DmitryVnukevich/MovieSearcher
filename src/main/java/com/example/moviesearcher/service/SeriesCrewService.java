package com.example.moviesearcher.service;

import com.example.moviesearcher.entities.SeriesCrew;
import com.example.moviesearcher.repository.SeriesCrewRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SeriesCrewService {

    private final SeriesCrewRepository seriesCrewRepository;

    public SeriesCrewService(SeriesCrewRepository seriesCrewRepository) {
        this.seriesCrewRepository = seriesCrewRepository;
    }

    @Transactional
    public SeriesCrew saveSeriesCrew(SeriesCrew seriesCrew) {
        return seriesCrewRepository.save(seriesCrew);
    }

    public List<SeriesCrew> findAllSeriesCrews() {
        return seriesCrewRepository.findAll();
    }

    public SeriesCrew findSeriesCrewById(Long id) {
        return seriesCrewRepository.findById(id).orElse(null);
    }

    @Transactional
    public void deleteSeriesCrew(Long id) {
        seriesCrewRepository.deleteById(id);
    }

    @Transactional
    public SeriesCrew updateSeriesCrew(SeriesCrew seriesCrew) {
        return seriesCrewRepository.save(seriesCrew);
    }
}
