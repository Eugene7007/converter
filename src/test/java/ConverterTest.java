import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConverterTest {

  private final Converter converter = new Converter();

  @Test
  void test() {
    BigDecimal value1 = BigDecimal.valueOf(1);
    BigDecimal value2 = BigDecimal.valueOf(2);
    BigDecimal value10 = BigDecimal.valueOf(10);
    BigDecimal value11 = BigDecimal.valueOf(11);
    BigDecimal value20 = BigDecimal.valueOf(20);
    BigDecimal value21 = BigDecimal.valueOf(21);
    BigDecimal value100 = BigDecimal.valueOf(100);
    BigDecimal value101 = BigDecimal.valueOf(101);
    BigDecimal value200 = BigDecimal.valueOf(200);
    BigDecimal value999 = BigDecimal.valueOf(999);
    BigDecimal value1000 = BigDecimal.valueOf(1000);
    BigDecimal value10000 = BigDecimal.valueOf(10_000);
    BigDecimal value100000 = BigDecimal.valueOf(100_000);
    BigDecimal value1000000 = BigDecimal.valueOf(1_000_000);
    BigDecimal value1000000000 = BigDecimal.valueOf(1_000_000_000);

    assertEquals(converter.convert(value1), "bir so'm");
    assertEquals(converter.convert(value2), "ikki so'm");

    assertEquals(converter.convert(value10), "o'n so'm");
    assertEquals(converter.convert(value11), "o'n bir so'm");
    assertEquals(converter.convert(value20), "yigirma so'm");
    assertEquals(converter.convert(value21), "yigirma bir so'm");
    assertEquals(converter.convert(value100), "bir yuz so'm");
    assertEquals(converter.convert(value101), "bir yuz bir so'm");
    assertEquals(converter.convert(value200), "ikki yuz so'm");
    assertEquals(converter.convert(value999), "to'qqiz yuz to'qson to'qqiz so'm");
    assertEquals(converter.convert(value1000), "bir ming so'm");
    assertEquals(converter.convert(value10000), "o'n ming so'm");
    assertEquals(converter.convert(value100000), "bir yuz ming so'm");
    assertEquals(converter.convert(value1000000), "bir million so'm");
    assertEquals(converter.convert(value1000000000), "bir milliard so'm");
  }


}
