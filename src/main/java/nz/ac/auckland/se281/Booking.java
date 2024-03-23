package nz.ac.auckland.se281;

import java.util.ArrayList;

public class Booking {
  private Venue venue;
  private String date;
  private String dateOfBooking;
  private String customerEmail;
  private String attendeesCount;
  private String bookingReference;
  private ArrayList<Service> services = new ArrayList<>();

  public Booking(
      Venue venue,
      String date,
      String dateofBooking,
      String customerEmail,
      String attendeesCount,
      String bookingReference) {
    this.venue = venue;
    this.date = date;
    this.dateOfBooking = dateofBooking;
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

  public int getAttendeesCount() {
    return Integer.parseInt(attendeesCount);
  }

  public String getAttendeesCountString() {
    return attendeesCount;
  }

  public String getCustomerEmail() {
    return customerEmail;
  }

  public String getDateOfBooking() {
    return dateOfBooking;
  }

  public Venue getVenue() {
    return venue;
  }

  public ArrayList<Service> getServices() {
    return services;
  }
}
