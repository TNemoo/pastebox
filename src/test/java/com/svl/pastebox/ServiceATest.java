package com.svl.pastebox;

import com.svl.pastebox.api.response.ResponseDataStatus;
import com.svl.pastebox.exeptions.NotFoundEntityException;
import com.svl.pastebox.repositories.EntityA;
import com.svl.pastebox.repositories.IntRepositoryA;
import com.svl.pastebox.repositories.RepositoryAMap;
import com.svl.pastebox.services.ServiceA;
import com.svl.pastebox.services.ServiceImp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ServiceATest {

    @Autowired
    private ServiceImp serviceA;

    @MockBean
    private RepositoryAMap repositoryA;


    @Test
    public void notExistHash(){
        when(repositoryA.getByHash(anyString())).thenThrow(NotFoundEntityException.class);
        //при обращении к репозиторию по указанному hash должны получить кастомное исключение;
        assertThrows(NotFoundEntityException.class, () -> serviceA.getByHash("f4654638fg6wd"));
        //serviceA.getByHash обращается к репозиторию и получает ответ от заглушки, вместо БД;
    }

    @Test
    public void getExistHash() {
        EntityA o = new EntityA();

        LocalDateTime date = LocalDateTime.now();
        String hash = Integer.toHexString(7483647);//7230ff
        String s = "anything";

        o.setId(1);
        o.setHash(hash);
        o.setData(s);
        o.setPublic(true);
        o.setLifetime(date);

//        repositoryA.add(o); //тесты не должны записывать в БД никакие данные, если они не проверяют саму БД,
        //поэтому мы не пишем объект в БД, но делаем заглушку, которая имитирует возврат объекта из БД

        when(repositoryA.getByHash(hash)).thenReturn(o);
        //при обращении к репозиторию методом repositoryA.getByHash() вместо обращения происходит просто
        // возврат значения,т.е. нашего объекта "о" класса EntityA;

        ResponseDataStatus expected = new ResponseDataStatus(s,true);
        ResponseDataStatus actual = serviceA.getByHash(hash);
        //метод serviceA.getByHash() здесь вызывает наш заглушенный метод repositoryA.getByHash();

        assertEquals(expected,actual);
    }
}
