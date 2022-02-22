package com.bistros.gs.api;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import javax.validation.ConstraintDeclarationException;

class GsExceptionAdviceTest {

  @Nested
  @DisplayName("ValidationException 예외 발생시")
  class validationExceptionTest {
    @Test
    @DisplayName("메시지가 있다면 그대로 반환한다")
    public void m1() {
      var advice = new GsExceptionAdvice();
      var exception = new ConstraintDeclarationException("에~러!");
      var result = advice.validationException(new MockHttpServletRequest(), exception);

      assertThat(result).isEqualTo("에~러!");
    }

    @Test
    @DisplayName("메시지가 없다면 정의된 에러 메시지를 반환한다")
    public void m2() {
      var advice = new GsExceptionAdvice();
      var exception = new ConstraintDeclarationException();
      var result = advice.validationException(new MockHttpServletRequest(), exception);

      assertThat(result).isEqualTo(GsExceptionAdvice.ERROR_STRING);
    }
  }

}