package com.padel.service;

import com.padel.dto.SiteRequest;
import com.padel.dto.SiteResponse;
import com.padel.entity.Site;
import com.padel.exception.NotFoundException;
import com.padel.mapper.SiteMapper;
import com.padel.repository.SiteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SiteService {

    private final SiteRepository siteRepository;
    private final SiteMapper siteMapper;

    public List<SiteResponse> getAll() {
        return siteRepository.findAll().stream().map(siteMapper::toResponse).toList();
    }

    public SiteResponse getById(Long id) {
        Site site = siteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Site id: " + id));
        return siteMapper.toResponse(site);
    }

    public SiteResponse create(SiteRequest request) {
        Site site = siteMapper.toEntity(request);
        return siteMapper.toResponse(siteRepository.save(site));
    }

    public SiteResponse update(Long id, SiteRequest request) {
        Site site = siteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Site id: " + id));
        site.setNom(request.getNom());
        site.setAdresse(request.getAdresse());
        site.setNbTerrains(request.getNbTerrains());
        site.setHeureDebut(request.getHeureDebut());
        site.setHeureFin(request.getHeureFin());
        return siteMapper.toResponse(siteRepository.save(site));
    }

    public void delete(Long id) {
        siteRepository.deleteById(id);
    }
}
