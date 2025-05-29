package edu.kh.project.admin.model.service;

import edu.kh.project.board.model.dto.Board;
import edu.kh.project.member.model.dto.Member;

import java.util.List;

public interface AdminService {
    // 관리자 로그인
    Member login(Member inputMember);

    /** 7일 이내 가입 회원 조회
     * @return
     */
    List<Member> weekEnrollMember();

    /** 최대 조회 수 게시글 조회
     * @return
     */
    Board maxReadCount();

    /** 최대 좋아요 수 게시글 조회
     * @return
     */
    Board maxLikeCount();

    /** 최대 댓글 수 게시글 조회
     * @return
     */
    Board maxCommentCount();

}
