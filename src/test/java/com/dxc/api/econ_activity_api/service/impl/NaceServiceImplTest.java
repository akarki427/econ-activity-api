package com.dxc.api.econ_activity_api.service.impl;

import com.dxc.api.econ_activity_api.dto.NaceDto;
import com.dxc.api.econ_activity_api.entity.Nace;
import com.dxc.api.econ_activity_api.repository.NaceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class NaceServiceImplTest {

    @Mock
    private NaceRepository naceRepository;

    @InjectMocks
    private NaceServiceImpl naceService;

    private Nace testNace;
    private NaceDto testNaceDto;

    @BeforeEach
    void setUp() {

        testNace = new Nace();
        testNace.setId(1L);
        testNace.setOrderNumber(101L);
        testNace.setLevel(2L);
        testNace.setCode("junit-code");
        testNace.setParent("A");
        testNace.setDescription("junit-desc");
        testNace.setInclusion("junit-inc");
        testNace.setAdditionalInclusion("junit-addn-inc");
        testNace.setRulings("junit-ruling");
        testNace.setExclusion("junit-exclusion");
        testNace.setReference("junit-rev");

        testNaceDto = new NaceDto();
        testNaceDto.setOrderNumber(101L);
        testNaceDto.setLevel(2L);
        testNaceDto.setCode("junit-code");
        testNaceDto.setParent("A");
        testNaceDto.setDescription("junit-desc");
        testNaceDto.setInclusion("junit-inc");
        testNaceDto.setAdditionalInclusion("junit-addn-inc");
        testNaceDto.setRulings("junit-ruling");
        testNaceDto.setExclusion("junit-exclusion");
        testNaceDto.setReference("junit-rev");
    }

    @Test
    void testCreateNaceDetail_Success() {
        when(naceRepository.save(any(Nace.class))).thenReturn(testNace);

        NaceDto createdNace = naceService.createNaceDetail(testNaceDto);

        assertNotNull(createdNace);
        assertEquals(101L, createdNace.getOrderNumber());
        assertEquals("junit-code", createdNace.getCode());
        assertEquals("junit-desc", createdNace.getDescription());

        verify(naceRepository, times(1)).save(any(Nace.class));
    }

    @Test
    void testCreateNaceDetail_Failure() {
        when(naceRepository.save(any(Nace.class))).thenThrow(new RuntimeException("Database Error"));

        Exception exception = assertThrows(RuntimeException.class, () -> {
            naceService.createNaceDetail(testNaceDto);
        });

        assertEquals("Database Error", exception.getMessage());

        verify(naceRepository, times(1)).save(any(Nace.class));
    }

    @Test
    void testGetAllNaceByCode_Success() {
        when(naceRepository.findAllByCode("junit-code")).thenReturn(Collections.singletonList(testNace));

        List<NaceDto> naceList = naceService.getAllNaceByCode("junit-code");

        assertNotNull(naceList);
        assertFalse(naceList.isEmpty());
        assertEquals(1, naceList.size());
        assertEquals(101L, naceList.get(0).getOrderNumber());

        verify(naceRepository, times(1)).findAllByCode("junit-code");
    }

    @Test
    void testGetAllNaceByCode_NotFound() {
        when(naceRepository.findAllByCode("XYZ")).thenReturn(List.of());

        List<NaceDto> naceList = naceService.getAllNaceByCode("XYZ");

        assertNotNull(naceList);
        assertTrue(naceList.isEmpty());

        verify(naceRepository, times(1)).findAllByCode("XYZ");
    }
}