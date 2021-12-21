package lang;

public enum OrderSeparation {
  TEN_SEPARATION(10L),
  HUNDRED_SEPARATION(100L),
  THOUSAND_SEPARATION(1_000L),
  MILLION_SEPARATION(1_000_000L),
  BILLION_SEPARATION(1_000_000_000L),
  TRILLION_SEPARATION(1_000_000_000_000L);

  private final Long value;

  OrderSeparation(Long value) {
    this.value = value;
  }

  public Long getValue() {
    return value;
  }
}
