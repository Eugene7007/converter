package lang.uz;

import lang.Converter;
import lang.NumberValues;
import org.apache.commons.lang3.Range;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

import static lang.uz.NumOrders.*;
import static lang.OrderSeparation.*;

public class UzConverter extends Converter {

  private static final String CURRENCY_NAME = "so'm";
  private static final String CURRENCY_FRACTIONAL_NAME = "tiyin";

  private static final Range<Long> oneNumRange = Range.between(1L, 9L);
  private static final Range<Long> twoNumRange = Range.between(10L, 99L);
  private static final Range<Long> threeNumRange = Range.between(100L, 999L);

  public UzConverter(NumberValues numberValues) {
    super(numberValues);
  }

  @Override
  public String convert(BigDecimal number) {
    return String.join(" ",convertIntegerPart(number), convertFractionalPart(number));
  }

  private String convertIntegerPart(BigDecimal number) {
    long value = number.longValue();
    if (value == 0)
      return "";

    String convertingNum = "";
    StringBuilder result = new StringBuilder();
    Iterator<Long> chunks = makeChunks(value);
    Iterator<NumOrders> orders = makeOrders(value);

    while (orders.hasNext() && chunks.hasNext()) {
      Long num = chunks.next();

      if (oneNumRange.contains(num))
        convertingNum = convertUnitsToString(num);
      else if (twoNumRange.contains(num))
        convertingNum = convertTensToString(num);
      else if (threeNumRange.contains(num))
        convertingNum = convertHundredsToString(num);

      result.append(convertingNum);

      if (num > 0)
        result.append(orders.next().getName());
    }
    result.append(CURRENCY_NAME);

    return StringUtils.normalizeSpace(result.toString());
  }

  private String convertFractionalPart(BigDecimal number) {
    long value = number.remainder(BigDecimal.ONE).multiply(new BigDecimal(HUNDRED_SEPARATION.getValue())).longValue();
    if (value == 0 || value > 99)
      return "";

    String convertingNum = "";
    StringBuilder result = new StringBuilder();

    if (oneNumRange.contains(value))
      convertingNum = convertUnitsToString(value);
    else
      convertingNum = convertTensToString(value);

    result
      .append(convertingNum)
      .append(" ")
      .append(CURRENCY_FRACTIONAL_NAME);

    return StringUtils.normalizeSpace(result.toString());
  }

  private Iterator<Long> makeChunks(Long value) {
    Deque<Long> numbers = new ArrayDeque<>();
    while (value > 0) {
      numbers.addFirst(value % THOUSAND_SEPARATION.getValue());
      value /= THOUSAND_SEPARATION.getValue();
    }

    return numbers.iterator();
  }

  private Iterator<NumOrders> makeOrders(Long value) {
    Deque<NumOrders> orders = new ArrayDeque<>();
    orders.addFirst(BLANK);

    if (value / THOUSAND_SEPARATION.getValue() > 0)
      orders.addFirst(THOUSANDS);
    if (value / MILLION_SEPARATION.getValue() > 0)
      orders.addFirst(MILLIONS);
    if (value / BILLION_SEPARATION.getValue() > 0)
      orders.addFirst(BILLIONS);
    if (value / TRILLION_SEPARATION.getValue() > 0)
      orders.addFirst(TRILLIONS);

    return orders.iterator();
  }

  private String convertUnitsToString(Long value) {
    return getNumberValues().getStringPresentation(value);
  }

  private String convertTensToString(Long value) {
    Long units = value % TEN_SEPARATION.getValue();
    Long tens = value - units;

    return String.join(
      " ",
      getNumberValues().getStringPresentation(tens),
      getNumberValues().getStringPresentation(units));
  }

  private String convertHundredsToString(Long value) {
    Long tail = value % HUNDRED_SEPARATION.getValue();
    Long hundreds = value / HUNDRED_SEPARATION.getValue();

    return String.join(
      " ",
      getNumberValues().getStringPresentation(hundreds),
      getNumberValues().getStringPresentation(HUNDRED_SEPARATION.getValue()),
      convertTensToString(tail));
  }
}