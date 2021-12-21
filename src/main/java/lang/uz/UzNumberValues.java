package lang.uz;

import lang.NumberValues;

import java.util.HashMap;
import java.util.Map;

public class UzNumberValues implements NumberValues {
  private static final Map<Long, String> currency = new HashMap<>();

  public UzNumberValues() {
    currency.put(1L, "bir");
    currency.put(2L, "ikki");
    currency.put(3L, "uch");
    currency.put(4L, "to'rt");
    currency.put(5L, "besh");
    currency.put(6L, "olti");
    currency.put(7L, "yetti");
    currency.put(8L, "sakkiz");
    currency.put(9L, "to'qqiz");
    currency.put(10L, "o'n");
    currency.put(20L, "yigirma");
    currency.put(30L, "o'ttiz");
    currency.put(40L, "qirq");
    currency.put(50L, "ellik");
    currency.put(60L, "oltmish");
    currency.put(70L, "yetmish");
    currency.put(80L, "sakson");
    currency.put(90L, "to'qson");
    currency.put(100L, "yuz");
    currency.put(1000L, "ming");
    currency.put(1000000L, "million");
    currency.put(1000000000L, "milliard");
    currency.put(1000000000000L, "trillion");
  }

  @Override
  public String getStringPresentation(Long number) {
    return currency.getOrDefault(number, "");
  }
}
