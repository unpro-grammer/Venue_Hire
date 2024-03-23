package nz.ac.auckland.se281;

public class CateringService extends Service {

  private String cateringType;

  public CateringService(int costPerPerson, String cateringType, int numPeople) {
    super(costPerPerson * numPeople);
    this.cateringType = cateringType;
  }

  public String getCateringType() {
    return "Catering (" + cateringType + ")";
  }
}
