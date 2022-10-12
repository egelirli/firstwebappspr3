package com.egelirli.firstwebappspr3.todo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

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
		return "addTodo";
	}

	@RequestMapping(value = "add-todo", method = RequestMethod.POST)
	public String addTodo(
            @RequestParam String descript,
			ModelMap model) {
		//if authenticated
		todoService.addNewTodo(
							  (String) model.getAttribute("name"),
							  descript,
							  LocalDate.now().plusYears(1),
							  false);
		List<Todo> todos = todoService.findByUsername("asdadf");
		model.addAttribute("todos", todos);
		
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
