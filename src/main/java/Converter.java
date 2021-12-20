import org.apache.commons.lang3.Range;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

public class Converter {

  private static final Long TEN_ORDER = 10L;
  private static final Long HUNDRED_ORDER = 100L;
  private static final Long THOUSAND_ORDER = 1_000L;
  private static final Long MILLION_ORDER = 1_000_000L;
  private static final Long BILLION_ORDER = 1_000_000_000L;

  private static final UzNumberValues numberValues = new UzNumberValues();
  private static final Range<Long> oneNumRange = Range.between(1L, 9L);
  private static final Range<Long> twoNumRange = Range.between(10L, 99L);
  private static final Range<Long> threeNumRange = Range.between(100L, 999L);

  public String convert(BigDecimal numbers) {
    StringBuilder result = new StringBuilder();
    Iterator<Long> chunks = makeChunks(numbers.longValue());
    Iterator<NumOrders> orders = makeOrders(numbers.longValue());

    while (orders.hasNext() && chunks.hasNext()) {
      Long num = chunks.next();

      if (oneNumRange.contains(num))
        result.append(oneDigitToString(num));
      else if (twoNumRange.contains(num))
        result.append(twoDigitsToString(num));
      else if (threeNumRange.contains(num))
        result.append(threeDigitsToString(num));

      if (num > 0)
        result.append(orders.next().getName());
    }
    result.append("so'm");

    return StringUtils.normalizeSpace(result.toString());
  }

  private Iterator<Long> makeChunks(Long value) {
    Deque<Long> numbers = new ArrayDeque<>();
    while (value > 0) {
      numbers.addFirst(value % THOUSAND_ORDER);
      value /= THOUSAND_ORDER;
    }

    return numbers.iterator();
  }

  private Iterator<NumOrders> makeOrders(Long value) {
    Deque<NumOrders> orders = new ArrayDeque<>();
    orders.addFirst(NumOrders.BLANK);

    if (value / THOUSAND_ORDER > 0)
      orders.addFirst(NumOrders.THOUSANDS);
    if (value / MILLION_ORDER > 0)
      orders.addFirst(NumOrders.MILLIONS);
    if (value / BILLION_ORDER > 0)
      orders.addFirst(NumOrders.BILLIONS);

    return orders.iterator();
  }

  private String oneDigitToString(Long value) {
    return numberValues.getStringPresentation(value);
  }

  private String twoDigitsToString(Long value) {
    Long units = value % TEN_ORDER;
    Long tens = value - units;

    return String.join(" ", numberValues.getStringPresentation(tens), numberValues.getStringPresentation(units));
  }

  private String threeDigitsToString(Long value) {
    Long tail = value % HUNDRED_ORDER;
    Long hundreds = value / HUNDRED_ORDER;

    return String.format("%s %s %s", numberValues.getStringPresentation(hundreds), numberValues.getStringPresentation(HUNDRED_ORDER), twoDigitsToString(tail));
  }
}