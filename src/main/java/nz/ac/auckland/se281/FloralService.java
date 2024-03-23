package nz.ac.auckland.se281;

public class FloralService extends Service {

  private String floralType;
  private String typeName;

  public FloralService(int cost, String floralType) {
    super(cost);
    this.floralType = floralType;
    this.typeName = "Floral (" + this.floralType + ")";
  }

  @Override
  public void printAddedToBooking(String bookingReference) {
    MessageCli.ADD_SERVICE_SUCCESSFUL.printMessage(typeName, bookingReference);
  }

  @Override
  public void printInvoiceDeets() {
    MessageCli.INVOICE_CONTENT_FLORAL_ENTRY.printMessage(floralType, cost + "");
  }
}
