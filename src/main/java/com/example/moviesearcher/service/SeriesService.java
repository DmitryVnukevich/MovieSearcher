package com.example.moviesearcher.service;

import com.example.moviesearcher.entities.Series;
import com.example.moviesearcher.repository.SeriesRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SeriesService {

    private final SeriesRepository seriesRepository;

    public SeriesService(SeriesRepository seriesRepository) {
        this.seriesRepository = seriesRepository;
    }

    @Transactional
    public Series saveSeries(Series series) {
        return seriesRepository.save(series);
    }

    public List<Series> findAllSeries() {
        return seriesRepository.findAll();
    }

    public Series findSeriesById(Long id) {
        return seriesRepository.findById(id).orElse(null);
    }

    @Transactional
    public void deleteSeries(Long id) {
        seriesRepository.deleteById(id);
    }

    @Transactional
    public Series updateSeries(Series series) {
        return seriesRepository.save(series);
    }
}
