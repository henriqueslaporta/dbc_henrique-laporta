package br.com.dbc.locadora;

import br.com.dbc.locadora.rest.AbstractRestController;
import br.com.dbc.locadora.service.DateService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest
public abstract class LocadoraApplicationTests {

    @Test
    public void contextLoads() {
    }
    
    protected final double DELTA = 0.1d;

    protected MockMvc restMockMvc;

    @Autowired
    protected MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    protected PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @MockBean
    protected DateService dateService;
    
    protected abstract AbstractRestController getController();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.restMockMvc = MockMvcBuilders.standaloneSetup(getController())
                .setCustomArgumentResolvers(pageableArgumentResolver)
                .setMessageConverters(jacksonMessageConverter).build();
        
        LocalDateTime dateTimeMock = LocalDateTime.now();
        
        when(dateService.dateTimeNow()).thenReturn(dateTimeMock);
        
        LocalDate dateMock = LocalDate.now();
        
        when(dateService.dateNow()).thenReturn(dateMock);
        
    }

}
