package edu.kh.customer.model.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.kh.customer.model.dto.Customer;
import edu.kh.customer.model.mapper.CustomerMapper;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

	private final CustomerMapper mapper;

	@Override
	public void insertCustomer(Customer customer) {
		
		mapper.insertCustomer(customer);
	}
	
}
