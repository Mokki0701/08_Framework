package edu.kh.todo.model.service;

import java.util.List;
import java.util.Map;

import edu.kh.todo.model.dto.Todo;

public interface TodoService {

	/** 할 일 목록 + 완료된 할 일 개수 조회
	 * @return map
	 */
	Map<String, Object> selectAll();

	int addTodo(String todoTitle, String todoContent);

	Todo todoDetail(int todoNo);

	int deleteTodo(int todoNo);

	int updateTodo(Todo todo);

	int changeComplete(Todo todo);

	int countTodo();

	int sortNo(int count);

	int completeCount();

	List<Todo> selectList();

}
