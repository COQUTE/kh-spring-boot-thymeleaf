package edu.kh.project.admin.model.service;

import edu.kh.project.admin.model.mapper.AdminMapper;
import edu.kh.project.board.model.dto.Board;
import edu.kh.project.member.model.dto.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    // 7일 이내 가입 회원
    @Override
    public List<Member> weekEnrollMember() {
        return mapper.weekEnrollMember();
    }

    // 최대 조회 수 게시글
    @Override
    public Board maxReadCount() {
        return mapper.maxReadCount();
    }

    // 최대 좋아요 수 게시글
    @Override
    public Board maxLikeCount() {
        return mapper.maxLikeCount();
    }

    // 최대 댓글 수 게시글
    @Override
    public Board maxCommentCount() {
        return mapper.maxCommentCount();
    }
}
