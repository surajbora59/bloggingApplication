package com.app.blog;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.app.blog.controllers.CategoryController;
import com.app.blog.dto.CategoryDto;
import com.app.blog.service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class BlogApplicationTests {

	private MockMvc mockMvc;

	@Mock
	private CategoryService categoryService;

	@InjectMocks
	private CategoryController categoryController;

	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(categoryController).build();
	}

	@Test
	void testCreateCategory() throws Exception {
		CategoryDto categoryDto = new CategoryDto();
		categoryDto.setCategoryTitle("Test Category");

		when(categoryService.createCategory(categoryDto)).thenReturn(categoryDto);

		mockMvc.perform(MockMvcRequestBuilders.post("/api/category")
											  .contentType("application/json")
											  .content("{ \"name\":\"Test Category\" }"))
			   .andExpect(status().is4xxClientError());
	}

	@Test
	void testdate(){
//		LocalDateTime localDateTime= LocalDateTime.now();
//		System.out.println(localDateTime);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println( sdf.format(new Date()));
	}
}
