package edu.kh.project.myPage.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UploadFile {
	
	private int fileNo;				 // 파일 번호
	private String filePath;		 // 파일 요청 경로
	private String fileOriginalName; // 파일 원본명
	private String fileRename;		 // 파일 변경명
	private String fileUploadDate;	 // 업로드 날짜
	private int memberNo;			 // 업로드한 회원 번호
	// DTO 만들 때 관련된 테이블 컬럼과 반드시 동일하게 만들어야 하는 건 아니다!
	// 필요에 따라(JOIN) 필드를 더 늘리거나, 줄여도 된다!
	private String memberNickname;
}
