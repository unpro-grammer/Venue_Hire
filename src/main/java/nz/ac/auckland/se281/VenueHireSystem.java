package nz.ac.auckland.se281;

import java.util.ArrayList;
import nz.ac.auckland.se281.Types.CateringType;
import nz.ac.auckland.se281.Types.FloralType;

public class VenueHireSystem {

  ArrayList<Venue> venues = new ArrayList<>();
  ArrayList<String> allVenueCodes = new ArrayList<>();

  public VenueHireSystem() {}

  public void printVenues() {
    switch (venues.size()) {
      case 0:
        System.out.println(MessageCli.NO_VENUES);
        break;
      case 1:
        MessageCli.NUMBER_VENUES.printMessage("is", "one");
        break;
    }
  }

  public void createVenue(
      String venueName, String venueCode, String capacityInput, String hireFeeInput) {

    venueCode = venueCode.toUpperCase();
    boolean validVenueCreation = true;

    if (venueName.trim().isEmpty()) {
      validVenueCreation = false;
      MessageCli.VENUE_NOT_CREATED_EMPTY_NAME.printMessage("");
    } else if (allVenueCodes.contains(venueCode)) {
      validVenueCreation = false;
      for (Venue eachVenue : venues) {
        String existingCode = eachVenue.getVenueCode();
        boolean equals = existingCode == venueCode;
        if (equals) {
          String existingVenue = eachVenue.getVenueName();
          MessageCli.VENUE_NOT_CREATED_CODE_EXISTS.printMessage(venueCode, existingVenue);
        }
      }
    }

    if (validVenueCreation) {
      Venue currentVenue = new Venue(venueName, venueCode, capacityInput, hireFeeInput);
      venues.add(currentVenue);
      allVenueCodes.add(venueCode.toUpperCase());
      MessageCli.VENUE_SUCCESSFULLY_CREATED.printMessage(venueName, venueCode);
    }
  }

  public void setSystemDate(String dateInput) {
    // TODO implement this method
  }

  public void printSystemDate() {
    // TODO implement this method
  }

  public void makeBooking(String[] options) {
    // TODO implement this method
  }

  public void printBookings(String venueCode) {
    // TODO implement this method
  }

  public void addCateringService(String bookingReference, CateringType cateringType) {
    // TODO implement this method
  }

  public void addServiceMusic(String bookingReference) {
    // TODO implement this method
  }

  public void addServiceFloral(String bookingReference, FloralType floralType) {
    // TODO implement this method
  }

  public void viewInvoice(String bookingReference) {
    // TODO implement this method
  }
}
