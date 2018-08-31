package com.kkpa.tutorial.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kkpa.tutorial.controller.Mappings;
import com.kkpa.tutorial.controller.UserController;
import com.kkpa.tutorial.domain.User;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

  @Autowired
  private MockMvc mockMvc;


  @Autowired
  private UserController usrController;



  @Test
  public void givenAUserItShouldBeCreated() throws Exception {

    User user = new User();
    user.setAge(29);
    user.setName("Cristian");
    user.setId(30l);

    mockMvc.perform(post(Mappings.URI_USER + "/").contentType(MediaType.APPLICATION_JSON)
        .content(convertToJson(user))).andExpect(status().isCreated());

  }


  private byte[] convertToJson(User user) {
    ObjectMapper mapper = new ObjectMapper();
    try {
      return mapper.writeValueAsBytes(user);
    } catch (JsonProcessingException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return null;
  }


}
