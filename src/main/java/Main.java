import java.math.BigDecimal;

public class Main {

  private static final Converter converter = new Converter();

  public static void main(String[] args) {
    BigDecimal value = BigDecimal.valueOf(1000000.60);
    BigDecimal subValue = value.remainder(BigDecimal.ONE).multiply(new BigDecimal(100));

    String result = converter.convert(value);
    System.out.println(value.longValue() + " = " + result);
  }
}