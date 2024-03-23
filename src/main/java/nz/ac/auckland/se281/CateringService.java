package nz.ac.auckland.se281;

public class CateringService extends Service {

  private String cateringType;
  private String typeName;

  public CateringService(int costPerPerson, String cateringType, int numPeople) {
    super(costPerPerson * numPeople);
    this.cateringType = cateringType;
    this.typeName = "Catering (" + this.cateringType + ")";
  }

  @Override
  public void printAddedToBooking(String bookingReference) {
    MessageCli.ADD_SERVICE_SUCCESSFUL.printMessage(typeName, bookingReference);
  }

  @Override
  public void printInvoiceDeets() {
    MessageCli.INVOICE_CONTENT_CATERING_ENTRY.printMessage(cateringType, cost + "");
  }
}
