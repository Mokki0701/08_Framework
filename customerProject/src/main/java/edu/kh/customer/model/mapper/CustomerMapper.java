package edu.kh.customer.model.mapper;

import org.apache.ibatis.annotations.Mapper;

import edu.kh.customer.model.dto.Customer;

@Mapper
public interface CustomerMapper {

	void insertCustomer(Customer customer);

}
