package ru.gb.application.integrate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.gb.controller.GoodsController;
import ru.gb.entity.Good;
import ru.gb.repository.GoodsRepository;

import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = GoodsController.class)
public class GoodsControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GoodsRepository goodsRepo;

    @Before
    public void init() {
       given(goodsRepo.findById(1L)).willReturn(Optional.of(new Good(1L, "Product #1", 100f)));
       given(goodsRepo.findAll()).willReturn(List.of(new Good(1L, "Product #1", 100f),
               new Good(2L, "Product #2", 200f)));
       Good good = new Good(1L, "Product #1", 100f);
       given(goodsRepo.save(good)).willReturn(new Good(1L, "Product #1", 100f));
    }

    @Test
    public void shouldReturnGoodById() throws Exception {
        System.out.println(mockMvc.perform(get("/goods/1")).andReturn().getResponse().getContentAsString());
        mockMvc.perform(get("/goods/1")).andExpect(status().isOk())
                .andExpect(content()
                .contentType("application/json"))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Product #1"))
                .andExpect(jsonPath("$.price").value(100f));
    }

    @Test
    public void shouldReturnAllGoods() throws Exception {
        System.out.println(mockMvc.perform(get("/goods")).andReturn().getResponse().getContentAsString());
        mockMvc.perform(get("/goods")).andExpect(status().isOk())
                .andExpect(content().json("[{'id':1,'name':'Product #1','price':100.0}," +
                        "{'id':2,'name':'Product #2','price':200.0}]"));

    }

    @Test
    public void postSaveTest() throws Exception {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(new Good(1L, "Product #1", 100f));
        mockMvc.perform(post("/goods")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().json("{'id':1,'name':'Product #1','price':100.0}"));
    }

    @Test
    public void deleteGoodTest() throws Exception {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(new Good(1L, "Product #1", 100f));
        mockMvc.perform(delete("/goods/1"))
                .andExpect(status().isOk());
    }
}