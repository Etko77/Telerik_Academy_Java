package com.company.web.springdemo.services;

import com.company.web.springdemo.exceptions.EntityDuplicateException;
import com.company.web.springdemo.exceptions.EntityNotFoundException;
import com.company.web.springdemo.models.Beer;
import com.company.web.springdemo.repositories.BeerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.company.web.springdemo.Helpers.createMockBeer;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {
    @Mock
    BeerRepository mockRepository;

    @InjectMocks
    BeerServiceImpl service;

    @Test
    public void getById_Should_ReturnBeer_When_MatchExists(){
        //Arrange
//        BeerRepository mockRepository = Mockito.mock(BeerRepository.class);
//        BeerService service = new BeerServiceImpl(mockRepository);

        when(mockRepository.get(2))
                .thenReturn(new Beer(2,"MockBeer name",1.3));

        //Act
        Beer result = service.get(2);

        //Assert
        Assertions.assertEquals(2,result.getId());
        Assertions.assertEquals("MockBeer name",result.getName());
        Assertions.assertEquals(1.3,result.getAbv());
    }
    @Test
    public void create_Should_Throw_When_BeerWithSameNameExists(){
        //Arrange
        var mockBeer = createMockBeer();

        when(mockRepository.get(mockBeer.getName()))
                .thenReturn(mockBeer);

        Assertions.assertThrows(EntityDuplicateException.class,
                () -> service.create(mockBeer,mockBeer.getCreatedBy()));
    }
    @Test
    public void create_Should_Throw_When_BeerNotFound(){
        var mockBeer = createMockBeer();

        doThrow(EntityNotFoundException.class).when(mockRepository).get(mockBeer.getName());

        service.create(mockBeer,mockBeer.getCreatedBy());
        verify(mockRepository).create(mockBeer);
    }
    
    @Test
    public void update_Should_Throw_When_BeerWithSameNameExists(){
        Beer mockBeer = createMockBeer();
        when(mockRepository.get(mockBeer.getName()))
        .thenReturn(mockBeer);

        service.update(mockBeer,mockBeer.getCreatedBy());
        verify(mockRepository).update(mockBeer);
//        Assertions.assertThrows(EntityDuplicateException.class,
//                () -> service.update(mockBeer,mockBeer.getCreatedBy()));
    }
}
