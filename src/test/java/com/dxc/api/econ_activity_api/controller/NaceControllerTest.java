package com.dxc.api.econ_activity_api.controller;

import com.dxc.api.econ_activity_api.dto.NaceDto;
import com.dxc.api.econ_activity_api.service.NaceService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(NaceController.class)
class NaceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NaceService naceService;

    @Autowired
    private ObjectMapper objectMapper;

    private NaceDto testNaceDto;

    @BeforeEach
    void setUp() {
        testNaceDto = new NaceDto();
        testNaceDto.setOrderNumber(101L);
        testNaceDto.setLevel(2L);
        testNaceDto.setCode("junit-code");
        testNaceDto.setParent("A");
        testNaceDto.setDescription("junit-description");
        testNaceDto.setInclusion("junit-inclusion");
        testNaceDto.setAdditionalInclusion("junit-addn-incl");
        testNaceDto.setRulings("junit-rulings");
        testNaceDto.setExclusion("junit-exclusions");
        testNaceDto.setReference("junit-ref");
    }

    @Test
    void testCreateNaceDetail_Success() throws Exception {
        when(naceService.createNaceDetail(any(NaceDto.class))).thenReturn(testNaceDto);

        mockMvc.perform(post("/v1/api/nace")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testNaceDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.Order").value(101))
                .andExpect(jsonPath("$.Code").value("junit-code"))
                .andExpect(jsonPath("$.Description").value("junit-description"));
    }

    @Test
    void testCreateNaceDetail_Failure() throws Exception {
        when(naceService.createNaceDetail(any(NaceDto.class)))
                .thenThrow(new RuntimeException("Database Error"));

        mockMvc.perform(post("/v1/api/nace")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testNaceDto)))
                .andExpect(status().isInternalServerError())
                .andExpect(content().json("{\"error\":\"Error saving Nace Details\"}"));
    }

    @Test
    void testCreateNaceDetail_ValidationError() throws Exception {
        NaceDto invalidNaceDto = new NaceDto();

        mockMvc.perform(post("/v1/api/nace")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidNaceDto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.Order").doesNotExist())
                .andExpect(jsonPath("$.Code").doesNotExist())
                .andExpect(jsonPath("$.Description").doesNotExist());
    }

    @Test
    void testGetAllNaceByCode_Success() throws Exception {
        List<NaceDto> naceList = Collections.singletonList(testNaceDto);
        when(naceService.getAllNaceByCode("junit-code")).thenReturn(naceList);

        mockMvc.perform(get("/v1/api/nace/code/junit-code"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].Order").value(101))
                .andExpect(jsonPath("$[0].Code").value("junit-code"))
                .andExpect(jsonPath("$[0].Description").value("junit-description"));
    }

    @Test
    void testGetAllNaceByCode_NotFound() throws Exception {
        when(naceService.getAllNaceByCode("XYZ")).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/v1/api/nace/code/XYZ"))
                .andExpect(status().isNotFound());
    }
}