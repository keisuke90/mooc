class Container {
  private int item;

  public Container(int item) {
    this.item = item;
  }

  public void setItem(int item) {
    this.item = item;
  }

  public int getItem() {
    return item;
  }

  public static void main(String[] args) {
    Container count = new Container(0);
    helper(count);
    System.out.println(count.getItem());
  }

  public static void helper(Container x) {
    x.setItem(x.getItem() + 1);
  }
}
