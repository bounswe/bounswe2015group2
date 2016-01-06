//package edu.boun.cmpe451.group2.test;
//
//
////import org.junit.After;
//
//import edu.boun.cmpe451.group2.controller.RecipeController;
//import edu.boun.cmpe451.group2.model.RecipeModel;
//import edu.boun.cmpe451.group2.model.UserModel;
//import junit.framework.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mock.web.MockHttpServletRequest;
//import org.springframework.mock.web.MockHttpSession;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//
//
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"../../../../../../webapp/WEB-INF/applicationContext.xml"})
//@ContextConfiguration("file:src/main/webapp/WEB-INF/applicationContext.xml")
//@WebAppConfiguration
//
//public class RecipeControllerTestCase {
////    @InjectMocks
////    RecipeController controller;
////
////    @Mock
////    UserModel userModel;
////
////    @Mock
////    RecipeModel recipeModel;
////
////    MockMvc mockMvc;
//
//    @Autowired WebApplicationContext wac;
//    @Autowired MockHttpSession session;
//    @Autowired MockHttpServletRequest request;
//
//    private MockMvc mockMvc;
//
//    @Before
//    public void setUp(){
//        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
//    }
//
//    @Test
//    public void testUserHomePage() throws Exception{
//        Assert.assertEquals(5,5);
//        //this.mockMvc.perform(get("/")).andExpect(status().isOk());
//    }
//}
