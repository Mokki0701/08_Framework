package edu.kh.project.freeBoard.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.kh.project.freeBoard.model.mapper.FreeBoardMapper;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class FreeBoardServiceImpl implements FreeBoardService {
	
	private final FreeBoardMapper mapper;

	@Override
	public List<Map<String, Object>> selectBoardTypeList() {
		return mapper.selectBoardTypeList();
	}

}
