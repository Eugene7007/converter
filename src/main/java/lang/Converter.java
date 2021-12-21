package lang;

import java.math.BigDecimal;

public abstract class Converter {

  private final NumberValues numberValues;

  protected Converter(NumberValues numberValues) {
    this.numberValues = numberValues;
  }

  public abstract String convert(BigDecimal number);

  public NumberValues getNumberValues() {
    return numberValues;
  }
}
