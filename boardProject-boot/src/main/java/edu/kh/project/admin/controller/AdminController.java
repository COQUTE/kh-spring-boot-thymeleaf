package edu.kh.project.admin.controller;

import edu.kh.project.admin.model.service.AdminService;
import edu.kh.project.board.model.dto.Board;
import edu.kh.project.member.model.dto.Member;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:5173" /*, allowCredentials = "true"*/)
// , allowCredentials = "true" 클라이언트로부터 들어오는 쿠키 허용
@RequestMapping("admin")
@RequiredArgsConstructor
@SessionAttributes({"loginMember"})
public class AdminController {

    private final AdminService service;

    @PostMapping("login")
    public Member login(@RequestBody Member inputMember, Model model) {

        Member loginMember = service.login(inputMember);

        if(loginMember == null) {
            return null;
        }

        model.addAttribute("loginMember", loginMember);
        return loginMember;
    }

    /** 관리자 로그아웃
     * @return
     */
    @GetMapping("logout")
    public ResponseEntity<String> logout(HttpSession session) {

        // ResponseEntity
        // Spring에서 제공하는 Http 응답 데이터를
        // 커스터마이징 할 수 있도록 지원하는 클래스
        // -> HTTP 상태코드, 헤더, 응답 본문(body)을 모두 설정 가능
        try {
            session.invalidate(); // 세션 무효화 처리
            return ResponseEntity.status(HttpStatus.OK)
                    .body("로그아웃이 완료되었습니다."); // 200

        } catch (Exception e) {
            // 세션 무효화 중 예외 발생한 경우
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("로그아웃 중 예외 발생 : " + e.getMessage());
        }
    }

    // ------------------------------- 통계 -------------------------------

    /** 7일 이내 가입 회원 조회
     * @return
     */
    @GetMapping("weekEnrollMember")
    public ResponseEntity<Object> weekEnrollMember() {
        try {
            List<Member> member = service.weekEnrollMember();
            return ResponseEntity.status(HttpStatus.OK).body(member);

        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    /** 최대 조회 수 게시글 조회
     * @return
     */
    @GetMapping("maxReadCount")
    public ResponseEntity<Object> maxReadCount() {
        try {
            Board board = service.maxReadCount();
            return ResponseEntity.status(HttpStatus.OK).body(board);

        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    /** 최대 좋아요 수 게시글 조회
     * @return
     */
    @GetMapping("maxLikeCount")
    public ResponseEntity<Object> maxLikeCount() {
        try {
            Board board = service.maxLikeCount();
            return ResponseEntity.status(HttpStatus.OK).body(board);

        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    /** 최대 댓글 수 게시글 조회
     * @return
     */
    @GetMapping("maxCommentCount")
    public ResponseEntity<Object> maxCommentCount() {
        try {
            Board board = service.maxCommentCount();
            return ResponseEntity.status(HttpStatus.OK).body(board);

        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
