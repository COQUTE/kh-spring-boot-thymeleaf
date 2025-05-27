package edu.kh.project.admin.model.service;

import edu.kh.project.admin.model.mapper.AdminMapper;
import edu.kh.project.member.model.dto.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor=Exception.class)
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AdminMapper mapper;

    private final BCryptPasswordEncoder bcrypt;

    // 관리자 로그인
    @Override
    public Member login(Member inputMember) {

        Member loginMember = mapper.login(inputMember.getMemberEmail());

        if(loginMember == null) {
            return null;
        }

        if(!bcrypt.matches(inputMember.getMemberPw(), loginMember.getMemberPw())) {
            return null;
        }

        loginMember.setMemberPw(null);
        return loginMember;
    }
}
