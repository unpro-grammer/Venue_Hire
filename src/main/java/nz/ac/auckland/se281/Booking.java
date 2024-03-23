package nz.ac.auckland.se281;

public class Booking {
  private String venueCode;
  private String date;
  private String customerEmail;
  private int attendeesCount;

  public Booking(String venueCode, String date, String customerEmail, int attendeesCount) {
    this.venueCode = venueCode;
    this.date = date;
    this.customerEmail = customerEmail;
    this.attendeesCount = attendeesCount;
  }
}
