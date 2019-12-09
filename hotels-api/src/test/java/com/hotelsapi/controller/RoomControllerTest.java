package com.hotelsapi.controller;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.hotelsapi.controller.RoomController;
import com.hotelsapi.entity.Room;
import com.hotelsapi.service.RoomService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = RoomController.class)
public class RoomControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private RoomService roomService;
	
	String exampleRoomJson = "{\n" + 
			"	\"room_number\":101,\n" + 
			"	\"room_status\":\"AVAILABLE\",\n" + 
			"	\"room_price\":1600\n" + 
			"}";
	
	@Test
	public void verifyCreateRoom() throws Exception{
		
		Room room = new Room(1, 101, "Available", 1800);
		
		Mockito.when(roomService.createRoom(Mockito.any(Room.class))).thenReturn(room);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("http://localhost:8080/api/v1/hotel-api/rooms")
				.content(exampleRoomJson)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}
	
	@Test
	public void verifyGetBooking() throws Exception{
		Room room = new Room(1, 101, "BOOKED", 1800);
		Mockito.when(roomService.getBooking(Mockito.anyInt())).thenReturn(room);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"http://localhost:8080/api/v1/hotel-api/booking/1").accept(
				MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		String expected = "{room_id:1,room_number:101,room_status:BOOKED,room_price:1800}";

		System.out.println(expected);
		System.out.println(result.getResponse()
				.getContentAsString());
		
		JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), false);
	}
}
