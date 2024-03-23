package nz.ac.auckland.se281;

public class FloralService extends Service {

  private String floralType;

  public FloralService(int cost, String floralType) {
    super(cost);
    this.floralType = floralType;
  }

  @Override
  public void printAddedToBooking(String bookingReference) {
    MessageCli.ADD_SERVICE_SUCCESSFUL.printMessage("Floral (" + floralType + ")", bookingReference);
  }
}
