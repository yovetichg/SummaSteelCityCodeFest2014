package com.summa.codefest;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

@Controller
public class RestController {
	
	@RequestMapping(value = "/getusers", method = RequestMethod.GET, produces="application/json")
	public @ResponseBody String getUsers() {
		ObjectMapper objectMapper = new ObjectMapper();
		//objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
		
		User user1 = new User();
		user1.setId(1000);
		user1.setIP("1.1.1.1");

		User user2 = new User();
		user2.setId(2000);
		user2.setIP("2.2.2.2");
		
		List<User> list = new ArrayList<User>();
		list.add(user1);
		list.add(user2);

		ObjectWriter ow = objectMapper.writer();
		ow.with(SerializationFeature.WRAP_ROOT_VALUE);
		String str = null;
		try {
			str = ow.writeValueAsString(list);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println("User JSON is\n" + str);
		return str.toString();
	}

	@RequestMapping(value = "/getevents", method = RequestMethod.GET, produces="application/json")
	public @ResponseBody String getEvents(
				@RequestParam(value = "wrapper", required = false) String wrapper) {
		DAO dao = new DAO();
		EntityManager em = dao.getEntityManager();
		
		TypedQuery<Event> query = em.createQuery("SELECT e FROM Event e", Event.class);
		List<Event> events = query.getResultList();
		
		ObjectMapper objectMapper = new ObjectMapper();
		//objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
		
		ObjectWriter ow = objectMapper.writer();
		ow.with(SerializationFeature.WRAP_ROOT_VALUE);
		String str = null;
		try {
			str = ow.writeValueAsString(events);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		em.close();
		if (wrapper != null)
			return wrapper + "(" + str.toString() + ")";
		return str.toString();
	}

	@RequestMapping(value = "/getquestions", method = RequestMethod.GET, produces="application/json")
	public @ResponseBody String getQuestions(
					@RequestParam(value = "wrapper", required = false) String wrapper) {
		DAO dao = new DAO();
		EntityManager em = dao.getEntityManager();
		
		TypedQuery<Question> query = em.createQuery("SELECT q FROM Question q", Question.class);
		List<Question> questions = query.getResultList();
		
		ObjectMapper objectMapper = new ObjectMapper();
		//objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
		
		ObjectWriter ow = objectMapper.writer();
		ow.with(SerializationFeature.WRAP_ROOT_VALUE);
		String str = null;
		try {
			str = ow.writeValueAsString(questions);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		em.close();
		if (wrapper != null)
			return wrapper + "(" + str.toString() + ")";
		return str.toString();
	}
}
