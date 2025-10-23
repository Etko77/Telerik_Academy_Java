package com.company.web.springdemo.services;


import com.company.web.springdemo.exceptions.EntityNotFoundException;
import com.company.web.springdemo.helpers.AuthenticationHelper;
import com.company.web.springdemo.helpers.BeerMapper;
import com.company.web.springdemo.models.Beer;
import com.company.web.springdemo.models.BeerDto;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static com.company.web.springdemo.Helpers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class BeerControllerTests {
    @MockitoBean
    private BeerService mockService;
    @MockitoBean
    private AuthenticationHelper authenticationHelper;

    @MockitoBean
    private BeerMapper beerMapper;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getBeerById_Should_Return_StatusOk_When_BeerExists() throws Exception {
        var mockBeer = createMockBeer();
        Mockito.when(mockService.get(mockBeer.getId()))
                .thenReturn(mockBeer);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/beers/{id}",mockBeer.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(mockBeer.getName()));
    }
    @Test
    public void getBeerById_Should_Return_StatusNotFound_When_BeerDoesNotExist() throws Exception {
        var mockBeer = createMockBeer();
        Mockito.when(mockService.get(mockBeer.getId()))
                .thenThrow(EntityNotFoundException.class);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/beers/{id}",mockBeer.getId()))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
    @Test
    public void createBeer_Should_ReturnStatusOk_When_NewBeerISCreated() throws Exception{
        var mockBeer = createMockBeer();
        var mockUser = createMockUser();

        var mockBeerDto = createBeerDto();

        Mockito.when(authenticationHelper.tryGetUser(Mockito.any(HttpHeaders.class)))
                .thenReturn(mockUser);
        Mockito.when(beerMapper.fromDto(Mockito.any(BeerDto.class)))
                .thenReturn(mockBeer);
        Mockito.doNothing().when(mockService).create(Mockito.any(Beer.class), Mockito.eq(mockUser));


        mockMvc.perform(MockMvcRequestBuilders.post("/api/beers")
                        .header("Authorization", "Bearer token")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "name": "%s",
                                  "abv": %.2f,
                                  "styleId": %d
                                }
                                """.formatted(mockBeer.getName(), mockBeer.getAbv(), mockBeer.getStyle().getId())))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    public void createBeer_Should_Throw_When_NewBeerISCreated() throws Exception{
        var mockBeer = createMockBeer();
        var mockUser = createMockUser();
        mockBeer.getCreatedBy().setAdmin(false);
        var mockBeerDto = createBeerDto();

        Mockito.when(authenticationHelper.tryGetUser(Mockito.any(HttpHeaders.class)))
                .thenReturn(mockUser);
        Mockito.when(beerMapper.fromDto(Mockito.any(BeerDto.class)))
                .thenReturn(mockBeer);
        Mockito.doNothing().when(mockService).create(Mockito.any(Beer.class), Mockito.eq(mockUser));


        mockMvc.perform(MockMvcRequestBuilders.post("/api/beers")
                        .header("Authorization", "Bearer token")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "name": "%s",
                                  "abv": %.2f,
                                  "styleId": %d
                                }
                                """.formatted(mockBeer.getName(), mockBeer.getAbv(), mockBeer.getStyle().getId())))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }
}
