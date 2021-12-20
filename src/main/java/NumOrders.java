public enum NumOrders {
  BLANK(" "),
  THOUSANDS(" ming "),
  MILLIONS(" million "),
  BILLIONS(" milliard ");

  private final String name;
  NumOrders(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
