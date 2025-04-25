package edu.kh.project.myPage.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import edu.kh.project.member.model.dto.Member;
import edu.kh.project.myPage.model.dto.UploadFile;

@Mapper
public interface MyPageMapper {

	/** 회원 정보 수정
	 * @param inputMember
	 * @return
	 */
	public int updateInfo(Member inputMember);

	/** 회원 비밀번호 조회
	 * @param memberNo
	 * @return
	 */
	public String selectPw(int memberNo);

	/** 회원 비밀번호 변경
	 * @param paramMap
	 * @return
	 */
	public int changePw(Map<String, String> paramMap);

	/** 회원 탈퇴
	 * @param memberNo
	 * @return
	 */
	public int secession(int memberNo);

	/** 파일 정보를 DB에 삽입
	 * @param uf
	 * @return
	 */
	public int insertuploadFile(UploadFile uf);

	/** 파일 목록 조회
	 * @param memberNo
	 * @return
	 */
	public List<UploadFile> fileList(int memberNo);

	/** 프로필 이미지 변경
	 * @param member
	 * @return
	 */
	public int profile(Member member);
	
}
