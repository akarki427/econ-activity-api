package com.dxc.api.econ_activity_api.service.impl;

import com.dxc.api.econ_activity_api.dto.NaceDto;
import com.dxc.api.econ_activity_api.entity.Nace;
import com.dxc.api.econ_activity_api.repository.NaceRepository;
import com.dxc.api.econ_activity_api.service.NaceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NaceServiceImpl implements NaceService {
    private static final Logger LOGGER = LoggerFactory.getLogger(NaceServiceImpl.class);
    private static final String LOG_INF_SAVE_NACE = "Saving Nace Details for order id {}";
    private static final String LOG_INF_FETCH_NACE_BY_CODE = "Fetching Nace Details by code {}";
    private final NaceRepository naceRepository;

    public NaceServiceImpl(NaceRepository naceRepository) {
        this.naceRepository = naceRepository;
    }

    @Override
    public NaceDto createNaceDetail(NaceDto naceDto) {
        LOGGER.info(LOG_INF_SAVE_NACE, naceDto.getOrderNumber());
        Nace nace = naceRepository.save(convertToEntity(naceDto));
        return this.convertToDto(nace);
    }

    @Override
    public List<NaceDto> getAllNaceByCode(String code) {
        LOGGER.info(LOG_INF_FETCH_NACE_BY_CODE, code);

        List<Nace> naceList = this.naceRepository.findAllByCode(code);
        return naceList.stream().map(this::convertToDto).toList();

    }

    private Nace convertToEntity(NaceDto naceDto) {
        Nace nace = new Nace();
        BeanUtils.copyProperties(naceDto, nace);
        return nace;
    }

    private NaceDto convertToDto(Nace nace) {
        NaceDto naceDto = new NaceDto();
        BeanUtils.copyProperties(nace, naceDto, "id");
        return naceDto;
    }
}
