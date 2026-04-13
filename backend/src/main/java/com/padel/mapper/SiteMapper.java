package com.padel.mapper;

import com.padel.dto.SiteRequest;
import com.padel.dto.SiteResponse;
import com.padel.entity.Site;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SiteMapper {
    Site toEntity(SiteRequest request);
    SiteResponse toResponse(Site site);
}
