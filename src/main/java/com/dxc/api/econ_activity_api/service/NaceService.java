package com.dxc.api.econ_activity_api.service;

import com.dxc.api.econ_activity_api.dto.NaceDto;

import java.util.List;

public interface NaceService {
    NaceDto createNaceDetail(NaceDto naceDto);

    List<NaceDto> getAllNaceByCode(String code);
}
