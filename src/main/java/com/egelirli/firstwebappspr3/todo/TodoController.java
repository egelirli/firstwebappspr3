package com.egelirli.firstwebappspr3.todo;

import java.time.LocalDate;
import java.util.List;

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
public class TodoController {

	private TodoService todoService;


	public TodoController(TodoService todoService) {
		super();
		this.todoService = todoService;
	}

	@RequestMapping("list-todos")
	public String listAllTodos(ModelMap model) {
		List<Todo> todos = todoService.findByUsername("in28minutes");
		model.addAttribute("todos", todos);

		return "listTodos";
	}

	@RequestMapping(value = "add-todo", method = RequestMethod.GET)
	public String showAddTodo(ModelMap model) {
		//if authenticated
		String username = (String)model.get("name");
		Todo todo = new Todo(0, username, "Default Desc", LocalDate.now().plusYears(1), false);
		model.put("todo", todo);		
		return "addTodo";
	}

	@RequestMapping(value = "add-todo", method = RequestMethod.POST)
	public String addTodo(
			 ModelMap model,
			@Valid Todo todo,
			BindingResult result) {
		//if authenticated
		
		if(result.hasErrors()) {
			return "addTodo";
		}
		String username = (String)model.get("name");
		todoService.addNewTodo(
							  username,
							  todo.getDescription(),
							  LocalDate.now().plusYears(1),
							  false);
		//List<Todo> todos = todoService.findByUsername("asdadf");
		//model.addAttribute("todos", todos);
		
		return "redirect:list-todos";		
		
	}

	
	@RequestMapping(value = "delete-todo", method = RequestMethod.GET)
	public String deleteTodo(
            @RequestParam int  id) {
		//if authenticated
		 boolean isDeleted = todoService.deleteById(id);

		return "redirect:list-todos";		
		
	}
	
	@RequestMapping(value = "update-todo", method = RequestMethod.GET)
	public String showUpdateTodo(
			ModelMap model,
            @RequestParam int  id) {
		//if authenticated
        
		Todo todo = todoService.findTodoById(id);
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
		
		todoService.modifyTodo(todo);
		
		return "redirect:list-todos";		
		
	}
	
	
	
//	@RequestMapping(value = "add-todo", method = RequestMethod.POST)
//	public String addTodo(
//            @RequestParam String descript,
//			@RequestParam LocalDate targetDate,
//			@RequestParam boolean isDone,
//			ModelMap model) {
//		//if authenticated
//		//List<Todo> todos = todoService.addNewTodo(descript, targetDate, isDone);
//		//model.addAttribute("todos", todos);
//
//		return "redirect:list-todos";		
//		
//	}

	
}
