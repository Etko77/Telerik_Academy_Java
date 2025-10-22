package com.company.web.springdemo.services;

import com.company.web.springdemo.exceptions.AuthorizationException;
import com.company.web.springdemo.exceptions.EntityDuplicateException;
import com.company.web.springdemo.exceptions.EntityNotFoundException;
import com.company.web.springdemo.models.Beer;
import com.company.web.springdemo.models.User;
import com.company.web.springdemo.repositories.BeerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.company.web.springdemo.Helpers.createMockBeer;
import static com.company.web.springdemo.Helpers.createMockUser;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BeerServiceTests {
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

        assertThrows(EntityDuplicateException.class,
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
    public void create_Should_CallRepository_When_BeerWithSameNameExists(){
        var mockBeer = createMockBeer();

        when(mockRepository.get(mockBeer.getName()))
        .thenReturn(mockBeer);

        Assertions.assertThrows(EntityDuplicateException.class,
                ()-> service.create(mockBeer,mockBeer.getCreatedBy()));
        Mockito.verify(mockRepository,never()).create(mockBeer);
    }
    
    @Test
    public void update_Should_Throw_When_BeerWithSameNameExists(){
        Beer mockBeerToUpdate = createMockBeer();
        Beer mockBeer = createMockBeer();
        mockBeerToUpdate.setId(2);
        System.out.println(mockBeerToUpdate.getName());
        System.out.println(mockBeer.getName());

        when(mockRepository.get(mockBeerToUpdate.getId()))
                .thenReturn(mockBeerToUpdate);
        when(mockRepository.get(mockBeerToUpdate.getName()))
                .thenReturn(mockBeer);

//        when(mockRepository.get(mockBeerToUpdate.getName())).thenReturn(mockBeerToUpdate); // for duplicate check

        Assertions.assertThrows(EntityDuplicateException.class,
                () -> service.update(mockBeerToUpdate,mockBeerToUpdate.getCreatedBy()));

//        verify(mockRepository,Mockito.times(1)).update(Mockito.any(Beer.class));

    }

    @Test
    public void update_Should_Pass_When_BeerNotFoundn(){
        var mockBeer = createMockBeer();
        var anotherBeer = createMockBeer();
        anotherBeer.setId(2);

        Mockito.when(mockRepository.get(Mockito.anyInt()))
                .thenReturn(mockBeer);
        Mockito.when(mockRepository.get(Mockito.anyString()))
                        .thenThrow(EntityNotFoundException.class);

        service.update(mockBeer,createMockUser());
        Assertions.assertEquals(mockBeer,
                service.get(mockBeer.getId()));
    }
    @Test
    public void checkModifyPermissions_Should_Throw_AuthorizationException_When_UserIsNotAdmin(){
            User creator = new User();
            creator.setId(1);
            creator.setAdmin(false);

            User otherUser = new User();
            otherUser.setId(2);
            otherUser.setAdmin(false);

            Beer beer = new Beer();
            beer.setId(5);
            beer.setCreatedBy(creator);

            when(mockRepository.get(5)).thenReturn(beer);

            assertThrows(AuthorizationException.class,
                    () -> service.checkModifyPermissions(5,otherUser));
    }
    @Test
    public void delete_Should_Work_When_ValidParams(){
        var mockBeer = createMockBeer();

        when(mockRepository.get(1)).thenReturn(mockBeer);
        service.delete(1,mockBeer.getCreatedBy());
        verify(mockRepository).delete(1);
    }
    @Test
    public void checkModifyPermissions_Should_Throw_AuthorizationException_When_UserIsNotCreator(){
        User creator = createMockUser();
        creator.setId(1);
        User otherUser = createMockUser();
        otherUser.setId(2);
        Beer beer = new Beer();
        beer.setId(5);
        beer.setCreatedBy(creator);

        when(mockRepository.get(5)).thenReturn(beer);

        assertThrows(AuthorizationException.class,
                () -> service.checkModifyPermissions(5,otherUser));
    }
}
