package fi.haagahelia.course;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser; 
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


@SpringBootTest
@AutoConfigureMockMvc
public class WebLayerTest {
	
	@Autowired
	private MockMvc mockMvc;

	@Test
	@WithMockUser(roles = "USER")
	public void testGetBookListEndpoint() throws Exception {
		
		/*this.mockMvc.perform(MockMvcRequestBuilders
				.post("/login")                  // Adjust the actual login endpoint
				.param("username", "user")  // Replace with a valid username
				.param("password", "$2a$10$KVK1CB0bzjnxo42BQ3y1nOha7LJLIRTWS5MTtXVJqKGWidpNcda3K"))  // Replace with a valid password
				.andExpect(status().is3xxRedirection());

			// After logging in, access the "/booklist" page
			this.mockMvc.perform(get("/booklist"))
				.andDo(print())
				.andExpect(status().isOk())
				;
		mockMvc.perform(get("/booklist"))
        .andExpect(status().is3xxRedirection()); // Your expected redirection status
        */

		 
  }


}
