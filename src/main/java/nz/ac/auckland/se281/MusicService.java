package nz.ac.auckland.se281;

public class MusicService extends Service {

  public MusicService(int cost) {
    super(cost);
  }

  @Override
  public void printAddedToBooking(String bookingReference) {
    MessageCli.ADD_SERVICE_SUCCESSFUL.printMessage("Music", bookingReference);
  }

  @Override
  public void printInvoiceDeets() {
    MessageCli.INVOICE_CONTENT_MUSIC_ENTRY.printMessage(cost + "");
  }
}
