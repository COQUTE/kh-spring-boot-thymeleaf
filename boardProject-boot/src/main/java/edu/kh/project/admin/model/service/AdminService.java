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

    /** 탈퇴한 회원 목록 조회
     * @return
     */
    List<Member> selectWithdrawnMemberList();

    /** 삭제된 게시글 목록 조회
     * @return
     */
    List<Board> selectDeleteBoardList();

    /** 탈퇴 회원 복구
     * @param memberNo 
     * @return
     */
    int restoreMember(int memberNo);

    /** 삭제된 게시글 복구
     * @param boardNo 
     * @return
     */
    int restoreBoard(int boardNo);
}
