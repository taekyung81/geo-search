package com.bistros.gs.application.search.filter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("공백 제거 확인")
class PlaceNameFilterTest {

  @Test
  @DisplayName("정상적으로 공백이 제거된다")
  public void testWhiteSpaceFilter() {
    var filter = new WhiteSpaceFilter();
    assertAll(
        () -> assertThat(filter.apply(" 테 스 트 ")).isEqualTo("테스트"),
        () -> assertThat(filter.apply("여러   공 백")).isEqualTo("여러공백"),
        () -> assertThat(filter.apply("찜닭")).isEqualTo("찜닭")
    );
  }

  @Test
  @DisplayName("TagNameFilter는 HTML 태그가 제거된다")
  public void testHtmlTagRemoveFilter() {
    var filter = new TagNameFilter();
    assertAll(
        () -> assertThat(filter.apply("<b>찜닭</b>")).isEqualTo("찜닭"),
        () -> assertThat(filter.apply("<b>테스트")).isEqualTo("테스트")
    );
  }

  @Test
  @DisplayName("TagNameFilter는 특수문자는 그대로 남는다")
  public void testHtmlTagRemoveFilter2() {
    var filter = new TagNameFilter();
    assertAll(
        () -> assertThat(filter.apply("**학교")).isEqualTo("**학교"),
        () -> assertThat(filter.apply("리!모컨")).isEqualTo("리!모컨")
    );
  }
}