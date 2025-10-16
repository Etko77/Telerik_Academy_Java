package com.company.web.springdemo;

import com.company.web.springdemo.models.Beer;
import com.company.web.springdemo.models.BeerDto;
import com.company.web.springdemo.models.Style;
import com.company.web.springdemo.models.User;

public class Helpers {

    public static User createMockUser(){
        var mockUser = new User();
        mockUser.setId(1);
        mockUser.setFirstName("TestFname");
        mockUser.setLastName("TestLname");
        mockUser.setPassword("TestPassword");
        mockUser.setEmail("TestEmail");
        mockUser.setAdmin(true);

        return mockUser;
    }
    public static Beer createMockBeer(){
        var mockBeer = new Beer();
        mockBeer.setId(1);
        mockBeer.setName("Test Beer");
        mockBeer.setAbv(1.0);
        mockBeer.setCreatedBy(createMockUser());
        mockBeer.setStyle(createMockStyle());
        return mockBeer;

    }
    public  static Style  createMockStyle(){
        var mockStyle = new Style();
        mockStyle.setId(1);
        mockStyle.setName("Test Style");
         return mockStyle;
    }
    public static BeerDto createBeerDto(){
        var mockBeerDto = new BeerDto();
        mockBeerDto.setAbv(1.0);
        mockBeerDto.setName("Test Beer");
        mockBeerDto.setStyleId(createMockStyle().getId());
        return mockBeerDto;
    }
}
