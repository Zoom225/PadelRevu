package com.padel.mapper;

import com.padel.dto.MembreRequest;
import com.padel.dto.MembreResponse;
import com.padel.entity.Membre;
import com.padel.entity.Site;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface MembreMapper {

    @Mapping(target = "siteFavori", source = "siteFavoriId", qualifiedByName = "idToSite")
    Membre toEntity(MembreRequest request);

    @Mapping(target = "siteFavoriId", source = "siteFavori.id")
    @Mapping(target = "siteFavoriNom", source = "siteFavori.nom")
    MembreResponse toResponse(Membre membre);

    @Named("idToSite")
    default Site idToSite(Long id) {
        if (id == null) return null;
        Site site = new Site();
        site.setId(id);
        return site;
    }
}
