package edu.kh.checkMember.member.model.mapper;

import org.apache.ibatis.annotations.Mapper;

import edu.kh.checkMember.member.model.dto.User;

@Mapper
public interface UserMapper {

    public User searchMember(String memberId);

}
