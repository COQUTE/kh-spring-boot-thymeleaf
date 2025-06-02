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

    // ------------------ 복구 ------------------

    /** 탈퇴한 회원 목록 조회
     * @return 
     */
    @GetMapping("withdrawnMemberList")
    public ResponseEntity<Object> selectWithdrawnMemberList() {
        // 성공 시 List<Member> 반환, 실패 시 String 반환 -> Object
        try {
            List<Member> withdrawnMemberList = service.selectWithdrawnMemberList();
            return ResponseEntity.status(HttpStatus.OK).body(withdrawnMemberList);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("탈퇴한 회원 목록 조회 중 문제 발생 : " + e.getMessage());
        }
    }

    /** 삭제된 게시글 목록 조회
     * @return 
     */
    @GetMapping("deleteBoardList")
    public ResponseEntity<Object> selectDeleteBoardList() {
        // 성공 시 List<Board> 반환, 실패 시 String 반환 -> Object
        try {
            List<Board> deleteBoardList = service.selectDeleteBoardList();
            return ResponseEntity.status(HttpStatus.OK).body(deleteBoardList);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("삭제된 게시글 목록 조회 중 문제 발생 : " + e.getMessage());
        }
    }

    /** 탈퇴 회원 복구
     * @param member 
     * @return
     */
    @PutMapping("restoreMember")
    public ResponseEntity<Object> restoreMember(@RequestBody Member member) {
        try {

            int result = service.restoreMember(member.getMemberNo());

            if(result > 0) {
                return ResponseEntity.status(HttpStatus.OK)
                        .body(member.getMemberNo() + " 회원 복구 완료");
            } else {
                // BAD_REQUEST : 400 -> 요청구문이 잘못되었거나 유효하지 않음.
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("유효하지 않은 회원 번호 : " + member.getMemberNo());
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("탈퇴 회원 복구 중 문제 발생 : " + e.getMessage());
        }
    }

    /** 삭제된 게시글 복구
     * @param board
     * @return
     */
    @PutMapping("restoreBoard")
    public ResponseEntity<Object> restoreBoard(@RequestBody Board board) {
        try {
            
            int result = service.restoreBoard(board.getBoardNo());
            
            if(result > 0) {
                return ResponseEntity.status(HttpStatus.OK)
                        .body(board.getBoardNo() + " 게시글 복구 완료");
            } else {
                // BAD_REQUEST : 400 -> 요청구문이 잘못되었거나 유효하지 않음.
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("유효하지 않은 게시글 번호 : " + board.getBoardNo());
            }
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("삭제된 게시글 복구 중 문제 발생 : " + e.getMessage());
        }
    }
    
    /** 관리자 계정 발급
     * @param member
     * @return
     */
    @PostMapping("createAdminAccount")
    public ResponseEntity<String> createAdminAccount(@RequestBody Member member) {
        try {
            
            // 1. 기존에 있는 이메일인지 검사
            int checkEmail = service.checkEmail(member.getMemberEmail());
        
            // 2. 있으면 발급 안 함
            if(checkEmail > 0) {
                // HttpStatus.CONFLICT (409) : 요청이 서버의 현재 상태와 충돌할 때 사용
                // == 이미 존재하는 리소스(email) 때문에 새로운 리소스를 만들 수 없다.
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("이미 사용중인 이메일입니다.");
            }
            
            // 3. 없으면 새로 발급
            String accountPw = service.createAdminAccount(member);
            
            // HttpStatus.OK (200) : 요청이 정상적으로 처리되었으나 기존 리소스에 대한 단순 처리
            // HttpStatus.CREATED (201) : 자원이 성공적으로 생성되었음을 나타냄
            return ResponseEntity.status(HttpStatus.CREATED).body(accountPw);
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR) // 500
                    .body("관리자 계정 생성 중 문제 발생(서버 문의 바람)");
        }
    }
    
    /** 관리자 목록 조회
     * @return 
     */
    @GetMapping("adminMemberList")
    public ResponseEntity<Object> adminMemberList() {
        try {
            List<Member> adminMemberList = service.selectAdminMemberList();
            return ResponseEntity.status(HttpStatus.OK).body(adminMemberList);
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR) // 500
                    .body("관리자 목록 조회 중 문제 발생(서버 문의 바람)");
        }
    }
}