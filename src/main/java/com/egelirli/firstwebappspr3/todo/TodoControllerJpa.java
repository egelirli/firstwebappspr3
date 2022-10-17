package com.egelirli.firstwebappspr3.todo;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;


import jakarta.validation.Valid;

@Controller
@SessionAttributes("name")
public class TodoControllerJpa {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	private TodoRepository todoRepo;
	
	

	public TodoControllerJpa(
							 TodoRepository todoRepo) {
		super();
		this.todoRepo = todoRepo;
	}

	@RequestMapping("list-todos")
	public String listAllTodos(ModelMap model) {
		//String username = (String)model.get("name"); 
		String username = getLoggedInUSerName();
		//List<Todo> todos = todoService.findByUsername(username);
		
		logger.debug("In listAllTodos *** - username : {} ", username);
		List<Todo> todos =  todoRepo.findByUsername(username);
		model.addAttribute("todos", todos);

		return "listTodos";
	}

	@RequestMapping(value = "add-todo", method = RequestMethod.GET)
	public String showAddTodo(ModelMap model) {
		//if authenticated
		//String username = (String)model.get("name"); ??
		String username = getLoggedInUSerName();
		Todo todo = new Todo(0, username, "Default Desc", LocalDate.now().plusYears(1), false);
		model.put("todo", todo);		
		return "addTodo";
	}

	@RequestMapping(value = "add-todo", method = RequestMethod.POST)
	public String addTodo(
			 ModelMap model,
			@Valid Todo todo,
			BindingResult result) {
		
		if(result.hasErrors()) {
			return "addTodo";
		}
		String username = getLoggedInUSerName();
		
		todo.setUsername(username);
		todoRepo.save(todo);
		
		return "redirect:list-todos";		
		
	}

	
	@RequestMapping(value = "delete-todo", method = RequestMethod.GET)
	public String deleteTodo(
            @RequestParam int  id) {
		 todoRepo.deleteById(id);

		return "redirect:list-todos";		
		
	}
	
	@RequestMapping(value = "update-todo", method = RequestMethod.GET)
	public String showUpdateTodo(
			ModelMap model,
            @RequestParam int  id) {
		
		Todo todo = todoRepo.findById(id).get();
		if(todo != null) {
			model.put("todo", todo);
			return "modifyTodo";
		}else {
			return "redirect:list-todos";
		}
		
	}

	@RequestMapping(value = "update-todo", method = RequestMethod.POST)
	public String updateTodo(
			 ModelMap model,
			@Valid Todo todo,
			BindingResult result) {
		//if authenticated
        
		if(result.hasErrors()) {
			return "modifyTodo";
		}
		
		todo.setUsername(getLoggedInUSerName());
		todoRepo.save(todo);
		
		return "redirect:list-todos";		
		
	}
	
	private String getLoggedInUSerName() {
		
		Authentication  authent =
					SecurityContextHolder.getContext().getAuthentication();
		return authent.getName();
	}	
	
}
