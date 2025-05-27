package edu.kh.project.admin.model.mapper;

import edu.kh.project.member.model.dto.Member;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminMapper {

    Member login(String memberEmail);
}
