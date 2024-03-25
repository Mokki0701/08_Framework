package edu.kh.todo.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.kh.todo.model.dto.Todo;
import edu.kh.todo.model.mapper.TodoMapper;


//--------------------------------------------------
//@Transactional
//- 트랜잭션 처리를 수행하라고 지시하는 어노테이션
//(== 선언적 트랜잭션 처리)

//- 정상 코드 수행 시 COMMIT

//- 기본값 : Service 내부 코드 수행 중 RuntimeException 발생 시 rollback

//- rollbackFor 속성 : 어떤 예외가 발생했을 때 rollback할지 지정
//---------------------------------------------------


@Transactional(rollbackFor = Exception.class)
@Service // 비즈니스 로직(데이터 가공, 트랜잭션 처리) 역할 명시
		 // + Bean 등록
public class TodoServiceImpl implements TodoService {
	
	@Autowired // DI
	private TodoMapper mapper;
	
	@Override
	public Map<String, Object> selectAll() {
		
		// 1. 할 일 목록 조회
		List<Todo> todoList = mapper.selectAll();
		
		// 2. 완료된 할 일 결과 반환
		int completeCount = mapper.getCompleteCount();
		
		Map<String, Object> map = new HashMap<>();
		map.put("todoList", todoList);
		map.put("completeCount", completeCount);
		
		return map;
	}

	@Override
	public int addTodo(String todoTitle, String todoContent) {
		
		// Connection 생성/반환 X
		// 트랜잭션 제어 처리 -> @Transactional 어노테이션
		
		// Mybatis에서 SQL에 전달할 수 있는 파라미터의 개수는
		// 오직 1개!!!
		// -> todoTitle, todoContent를 Todo DTO로 묶어서 전달
		
		Todo todo = new Todo();
		todo.setTodoTitle(todoTitle);
		todo.setTodoContent(todoContent);
		
		return mapper.addTodo(todo);
	}

	@Override
	public Todo todoDetail(int todoNo) {
		
		Todo todo = mapper.todoDetail(todoNo);
		
		return todo;
	}

	// --------------------------
	
	@Override
	public int deleteTodo(int todoNo) {
		return mapper.deleteTodo(todoNo);
	}

	@Override
	public int updateTodo(Todo todo) {
		
		return mapper.updateTodo(todo);
	}

	@Override
	public int changeComplete(Todo todo) {
		return mapper.changeComplete(todo);
	}

	@Override
	public int countTodo() {
		return mapper.countTodo();
	}

	@Override
	public int sortNo(int count) {
		int result = 0;
		
		// 리스트로 각각의 todoNo를 알아와야 변경이 가능하겠는데?
		List<Integer> todoList = mapper.selectNo();
		
		for(int i = 0; i < count; i++) {
			Map<String, Integer> todoMaps = new HashMap<>();
			todoMaps.put("originalNum", todoList.get(i));
			todoMaps.put("changeNum", i+1);
			result += mapper.sortNo(todoMaps);
			
		}
		
		
		return result;
	}

	@Override
	public int completeCount() {
		return mapper.getCompleteCount();
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
