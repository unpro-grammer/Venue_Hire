package nz.ac.auckland.se281;

import java.util.ArrayList;

public class Booking {
  private Venue venue;
  private String date;
  private String customerEmail;
  private String attendeesCount;
  private String bookingReference;
  private ArrayList<Service> services = new ArrayList<>();

  public Booking(
      Venue venue,
      String date,
      String customerEmail,
      String attendeesCount,
      String bookingReference) {
    this.venue = venue;
    this.date = date;
    this.customerEmail = customerEmail;
    this.attendeesCount = attendeesCount;
    this.bookingReference = bookingReference;
  }

  public String getVenueCodeofBooking() {
    return venue.getVenueCode();
  }

  public String getDate() {
    return date;
  }

  public String getVenueNameofBooking() {
    return venue.getVenueName();
  }

  public String getReference() {
    return bookingReference;
  }

  public void addService(Service service) {
    services.add(service);
  }
}
