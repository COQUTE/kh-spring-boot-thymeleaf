package edu.kh.demo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// Spring EL 같은 경우 DTO 객체 출력할 때 getter가 필수 작성되어 있어야 함!
// -> ${Student.name} == ${Student.getName()}

@Data // Getter + Setter + ToString
@NoArgsConstructor // 기본 생성자
@AllArgsConstructor // 모든 필드 초기화용 매개변수 생성자
@Builder
public class Student {

	private String studentNo; // 학생 번호
	private String name;	  // 이름
	private int age;		  // 나이
}
