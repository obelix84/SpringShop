package ru.gb.application.integrate;

import org.junit.After;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import ru.gb.entity.Good;
import ru.gb.repository.GoodsRepository;

import java.lang.ref.SoftReference;
import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase//(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource("file:C:\\Users\\obelix\\IdeaProjects\\GeekbrainsSpringShop\\lesson-5-6\\lesson5Rest\\src\\test\\java\\ru\\gb\\application\\integrate\\int-test.properties")
public class GoodsRepositoryIntegrationTest {

        @Autowired
        private GoodsRepository goodRepo;

        @After
        public void rollbackAll() throws Exception {
            goodRepo.deleteAll();
        }

        //тут нужно тестировать собственные методы для доступа к данным, но таковых тут нет.
        @Test
        public void shouldSaveAndFetchGood() throws Exception {
            Good good = new Good(1L, "Product #1", 100f);
            System.out.println(good);
            goodRepo.save(good);
            Optional<Good> newGood = goodRepo.findById(1L);
            Assertions.assertEquals(newGood.get(), good);
        }
}
