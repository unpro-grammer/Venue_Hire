package nz.ac.auckland.se281;

public class FloralService extends Service {

  public FloralService(int cost) {
    super(cost);
  }

  @Override
  public void printAddedToBooking(String floralName, String bookingReference) {
    MessageCli.ADD_SERVICE_SUCCESSFUL.printMessage(floralName, bookingReference);
  }
}
