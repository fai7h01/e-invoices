package com.accounting.einvoices.controller;

import com.accounting.einvoices.dto.CompanyDTO;
import com.accounting.einvoices.dto.RoleDTO;
import com.accounting.einvoices.dto.UserDTO;
import com.accounting.einvoices.repository.UserRepository;
import com.accounting.einvoices.service.CompanyService;
import com.accounting.einvoices.service.RoleService;
import com.accounting.einvoices.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @Mock
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleService roleService;

    @Mock
    private CompanyService companyService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void createUser() throws Exception {
        UserDTO userDTO = new UserDTO();
        userDTO.setFirstName("John");
        userDTO.setLastName("Smith");
        userDTO.setUsername("john@gmail.com");

        UserDTO savedUserDTO = new UserDTO();
        savedUserDTO.setFirstName("John");
        savedUserDTO.setLastName("Smith");
        savedUserDTO.setUsername("john@gmail.com");

        when(userRepository.findByUsername("john@gmail.com")).thenReturn(Optional.empty());

        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(1L);
        roleDTO.setDescription("Admin");
        when(roleService.findById(1L)).thenReturn(roleDTO);


        CompanyDTO companyDTO = new CompanyDTO();
        companyDTO.setId(1L);
        companyDTO.setTitle("Test Company");
        when(companyService.getByLoggedInUser()).thenReturn(companyDTO);

        when(userService.save(any(UserDTO.class))).thenReturn(savedUserDTO);

        MvcResult result = mvc.perform(post("/api/v1/user/create")
                        .content(new ObjectMapper().writeValueAsString(userDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();

        String jsonResponse = result.getResponse().getContentAsString();
        System.out.println("Response JSON: " + jsonResponse);

        mvc.perform(post("/api/v1/user/create")
                .content(toJsonString(userDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message").value("User is successfully created."));
    }


    @Test
    public void findAllUsers() throws Exception{

        ResultActions actions = mvc.perform(MockMvcRequestBuilders
                .get("/api/v1/user/list")
                .accept(MediaType.APPLICATION_JSON));
        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("User list is successfully retrieved."));

    }



    private static String toJsonString(final Object obj) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            objectMapper.registerModule(new JavaTimeModule());
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}