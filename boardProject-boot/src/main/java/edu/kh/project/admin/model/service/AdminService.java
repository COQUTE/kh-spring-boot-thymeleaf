package edu.kh.project.admin.model.service;

import edu.kh.project.member.model.dto.Member;

public interface AdminService {
    Member login(Member inputMember);
}
