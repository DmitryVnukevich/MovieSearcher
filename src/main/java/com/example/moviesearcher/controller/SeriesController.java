package com.example.moviesearcher.controller;

import com.example.moviesearcher.entities.Series;
import com.example.moviesearcher.service.SeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/series")
public class SeriesController {

    private final SeriesService seriesService;

    @Autowired
    public SeriesController(SeriesService seriesService) {
        this.seriesService = seriesService;
    }

    @PostMapping
    public Series createSeries(@RequestBody Series series) {
        return seriesService.saveSeries(series);
    }

    @GetMapping
    public List<Series> getAllSeries() {
        return seriesService.findAllSeries();
    }

    @GetMapping("/{id}")
    public Series getSeriesById(@PathVariable Long id) {
        return seriesService.findSeriesById(id);
    }

    @PutMapping("/{id}")
    public Series updateSeries(@PathVariable Long id, @RequestBody Series seriesDetails) {
        seriesDetails.setId(id);
        return seriesService.updateSeries(seriesDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteSeries(@PathVariable Long id) {
        seriesService.deleteSeries(id);
    }
}
