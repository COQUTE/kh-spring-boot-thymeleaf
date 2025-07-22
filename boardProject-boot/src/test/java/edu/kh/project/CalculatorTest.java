package edu.kh.project;
// JUnit 테스트 코드는 반드시 src/test/java 경로에 위치해야함!

// src/main/java : 실제 애플리케이션 코드
// src/test/java : 테스트 코드(JUnit 포함됨)

// 테스트 특징과 규칙
// 1. 기본적으로 JUnit5는 테스트 메서드의 실행 순서를 보장하지 않는다
// 2. 테스트는 순서에 의존하지 않도록 설계하는 것이 권장된다
// 3. 테스트는 독립적이어야 하며, 순서에 따라 실패 or 통과하면 안된다
// -> 테스트가 순서에 의존하면 좋은 테스트가 아님
// 4. 그래도 순서를 따지고 싶다면 @TestMethodOrder / @Order 사용할 수 있다


import edu.kh.project.test.Calculator;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CalculatorTest {

    private static Calculator calculator;
    
    // @BeforeAll, @AfterAll - static 메서드에서 사용해야함!
    @BeforeAll
    static void initAll() {
        // 모든 테스트 실행 전에 1번만 실행
        calculator = new Calculator();
        System.out.println("@BeforeAll - 테스트 시작 전 초기화");
    }
    
    @Test
    @Order(1)
    void testAdd() {
        System.out.println("testAdd 실행");
        assertEquals(30, calculator.add(10, 20), "10 + 20은 30이여야 합니다");
        // assertEquals(expected, actual) : 두 값이 같은가?
    }
    
    @Test
    void testSubstract() {
        System.out.println("testSubstract 실행");
        assertEquals(5, calculator.substract(15, 10), "15 - 10은 5여야 합니다");
    }
    
    @Test
    void testMultiply() {
        System.out.println("testMultiply 실행");
        assertEquals(25, calculator.multiply(5, 5), "5 * 5는 25여야 합니다");
    }
    
    @Test
    void testDivide() {
        System.out.println("testDivide 실행");
        assertEquals(2, calculator.divide(4, 2), "4 / 2는 2여야 합니다");
    }
    
    @Test
    void testDivideByZero() {
        System.out.println("testDivideByZero 실행");
        
        // assertThrows(Exception.class, executable) : 예외 발생 여부
        Exception exception = assertThrows(ArithmeticException.class, () -> {
            calculator.divide(4, 0);
        });
        
        assertEquals("0으로 나눌 수 없음", exception.getMessage());
    }
    
    @AfterAll
    static void end() {
        // 모든 테스트가 끈난 뒤 1번만 실행
        System.out.println("@AfterAll - 모든 테스트 완료");
    }
    
}
