package ru.gb.application.unit;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.gb.controller.GoodsController;
import ru.gb.entity.Good;
import ru.gb.repository.GoodsRepository;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.mock;

public class UnitTestGoodsController {

    GoodsController goodsController;

    @Before
    public void setUp() throws Exception {
        GoodsRepository goodsRepository = mock(GoodsRepository.class);

        Mockito.when(goodsRepository.findAll())
                .thenReturn(List.of(new Good(1L, "Product #1", 100f),
                        new Good(2L, "Product #2", 200f),
                        new Good(3L, "Product #3", 300f)));
        Mockito.when(goodsRepository.findById(1L))
                .thenReturn(Optional.of(new Good(1L, "Product #1", 100f)));
        Mockito.when(goodsRepository.findById(2L))
                .thenReturn(Optional.of(new Good(2L, "Product #2", 200f)));

        Mockito.when(goodsRepository.save(new Good(1L, "Product #1", 100f)))
                .thenReturn(new Good(1L, "Product #1", 100f));

        goodsController = new GoodsController(goodsRepository);
    }

    @Test
    public void getGoodsTest() throws Exception {
        ResponseEntity<List<Good>> response = goodsController.findAll();
        List<Good> testGoods = List.of(new Good(1L, "Product #1", 100f),
                new Good(2L, "Product #2", 200f),
                new Good(3L, "Product #3", 300f));
        Assertions.assertAll(
                () -> Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK),
                () -> Assertions.assertEquals(testGoods, response.getBody())
        );
    }

    @Test
    public void getExistsGoodsByIdTest() throws Exception {
        Good good1 = new Good(1L, "Product #1", 100f);
        Good good2 = new Good(2L, "Product #2", 200f);
        Assertions.assertAll(
                () -> Assertions.assertEquals(goodsController.findById(1L).getStatusCode(), HttpStatus.OK),
                () -> Assertions.assertEquals(goodsController.findById(1L).getBody(), good1),
                () -> Assertions.assertEquals(goodsController.findById(2L).getStatusCode(), HttpStatus.OK),
                () -> Assertions.assertEquals(goodsController.findById(2L).getBody(), good2)
        );
    }

    @Test
    public void getNotExistsGoodsByIdTest() throws Exception {
        Assertions.assertEquals(goodsController.findById(3L).getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    public void saveNewGoodTest() throws Exception {
        Good newGood = new Good(1L, "Product #1", 100f);
        ResponseEntity<Good> response = goodsController.save(newGood);
        Assertions.assertAll(
                () -> Assertions.assertEquals(response.getStatusCode(), HttpStatus.CREATED),
                () -> Assertions.assertEquals(response.getBody(), newGood)
        );
    }

    @Test
    public void deleteGoodTest() throws Exception {
        Assertions.assertAll(
                () -> Assertions.assertEquals(goodsController.deleteGood(1L), 200)
        );
    }

}
