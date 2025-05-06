package com.study.project.member.model.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.study.project.member.model.dto.Member;

@Mapper
public interface MemberMapper {

	Member login(String memberEmail);
	
	
}
