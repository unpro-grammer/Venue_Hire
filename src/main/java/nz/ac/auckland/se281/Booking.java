package nz.ac.auckland.se281;

public class Booking {
  private String venueCode;
  private String date;
  private String customerEmail;
  private String attendeesCount;
  private String bookingReference;

  public Booking(
      String venueCode,
      String date,
      String customerEmail,
      String attendeesCount,
      String bookingReference) {
    this.venueCode = venueCode;
    this.date = date;
    this.customerEmail = customerEmail;
    this.attendeesCount = attendeesCount;
    this.bookingReference = bookingReference;
  }
}
