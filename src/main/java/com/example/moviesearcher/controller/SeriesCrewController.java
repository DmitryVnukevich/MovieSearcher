package com.example.moviesearcher.controller;

import com.example.moviesearcher.entities.SeriesCrew;
import com.example.moviesearcher.service.SeriesCrewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/series-crew")
public class SeriesCrewController {

    private final SeriesCrewService seriesCrewService;

    @Autowired
    public SeriesCrewController(SeriesCrewService seriesCrewService) {
        this.seriesCrewService = seriesCrewService;
    }

    @PostMapping
    public SeriesCrew createSeriesCrew(@RequestBody SeriesCrew seriesCrew) {
        return seriesCrewService.saveSeriesCrew(seriesCrew);
    }

    @GetMapping
    public List<SeriesCrew> getAllSeriesCrews() {
        return seriesCrewService.findAllSeriesCrews();
    }

    @GetMapping("/{id}")
    public SeriesCrew getSeriesCrewById(@PathVariable Long id) {
        return seriesCrewService.findSeriesCrewById(id);
    }

    @PutMapping("/{id}")
    public SeriesCrew updateSeriesCrew(@PathVariable Long id, @RequestBody SeriesCrew seriesCrewDetails) {
        seriesCrewDetails.setId(id);
        return seriesCrewService.updateSeriesCrew(seriesCrewDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteSeriesCrew(@PathVariable Long id) {
        seriesCrewService.deleteSeriesCrew(id);
    }
}
