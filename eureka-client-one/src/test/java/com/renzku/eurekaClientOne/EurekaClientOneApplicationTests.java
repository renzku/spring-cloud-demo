package com.renzku.eurekaClientOne;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EurekaClientOneApplication.class)
public class EurekaClientOneApplicationTests {

	@Test
	public void contextLoads() {
	}

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext context;

	@Before
    public void setUp(){
	    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }

	@Test
    public void helloWorld() throws Exception{
	    Map<String,Object> map = new HashMap<>();
	    map.put("key", "value");
        MvcResult rst = mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andReturn();
        System.out.print("fj");
    }

}
