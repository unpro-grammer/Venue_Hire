package nz.ac.auckland.se281;

public class CateringService extends Service {

  private String cateringType;

  public CateringService(int cost, String cateringType) {
    super(cost);
    this.cateringType = cateringType;
  }
}
