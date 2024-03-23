package nz.ac.auckland.se281;

import java.util.ArrayList;

public class Venue {
  private String venueName;
  private String venueCode;
  private String capacityInput;
  private String hireFeeInput;
  private String nextAvailableDate;
  private ArrayList<Booking> allBookings = new ArrayList<>(); // past and future

  public Venue(
      String venueName,
      String venueCode,
      String capacityInput,
      String hireFeeInput,
      String nextAvailableDate) {
    this.venueName = venueName;
    this.venueCode = venueCode;
    this.capacityInput = capacityInput;
    this.hireFeeInput = hireFeeInput;
    this.nextAvailableDate = nextAvailableDate;
  }

  public String getVenueName() {
    return venueName;
  }

  public String getVenueCode() {
    return venueCode;
  }

  public String getCapacityInput() {
    return capacityInput;
  }

  public String getHireFeeInput() {
    return hireFeeInput;
  }

  public String getNextAvailableDate() {
    return nextAvailableDate;
  }

  public void setNextAvailableDate(String nextAvailableDate) {
    this.nextAvailableDate = nextAvailableDate;
  }

  public void addBookingInstance(Booking booking) {
    allBookings.add(booking);
  }

  public ArrayList<Booking> getBookingHistory() {
    return allBookings;
  }
}
