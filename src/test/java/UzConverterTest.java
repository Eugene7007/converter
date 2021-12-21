import lang.Converter;
import lang.uz.UzConverter;
import lang.uz.UzNumberValues;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UzConverterTest {

  private final Converter converter = new UzConverter(new UzNumberValues());

  @Test
  void testIntegerNumbers() {
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

    assertEquals("bir so'm", converter.convert(value1));
    assertEquals("ikki so'm", converter.convert(value2));

    assertEquals("o'n so'm", converter.convert(value10));
    assertEquals("o'n bir so'm", converter.convert(value11));
    assertEquals("yigirma so'm", converter.convert(value20));
    assertEquals("yigirma bir so'm", converter.convert(value21));
    assertEquals("bir yuz so'm", converter.convert(value100));
    assertEquals("bir yuz bir so'm", converter.convert(value101));
    assertEquals("ikki yuz so'm", converter.convert(value200));
    assertEquals("to'qqiz yuz to'qson to'qqiz so'm", converter.convert(value999));
    assertEquals("bir ming so'm", converter.convert(value1000));
    assertEquals("o'n ming so'm", converter.convert(value10000));
    assertEquals("bir yuz ming so'm", converter.convert(value100000));
    assertEquals("bir million so'm", converter.convert(value1000000));
    assertEquals("bir milliard so'm", converter.convert(value1000000000));
  }

  @Test
  void testDecimalNumbers() {
    BigDecimal value1 = BigDecimal.valueOf(100.12);
    assertEquals("bir yuz so'm o'n ikki tiyin", converter.convertWithFractionalPart(value1));

  }

}
