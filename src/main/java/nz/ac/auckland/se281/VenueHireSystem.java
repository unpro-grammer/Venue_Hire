package nz.ac.auckland.se281;

import java.util.ArrayList;
import nz.ac.auckland.se281.Types.CateringType;
import nz.ac.auckland.se281.Types.FloralType;

public class VenueHireSystem {

  ArrayList<Venue> venues = new ArrayList<>();
  ArrayList<String> allVenueCodes = new ArrayList<>();

  public VenueHireSystem() {}

  public void printVenues() {
    String numberToPrint = null;
    String verb = "are";
    String suffix = "s";
    switch (venues.size()) {
      case 0:
        System.out.println(MessageCli.NO_VENUES);
        break;
      case 1:
        numberToPrint = "one";
        verb = "is";
        suffix = "";
        break;
      case 2:
        numberToPrint = "two";
        break;
      case 3:
        numberToPrint = "three";
        break;
      case 4:
        numberToPrint = "four";
        break;
      case 5:
        numberToPrint = "five";
        break;
      case 6:
        numberToPrint = "six";
        break;
      case 7:
        numberToPrint = "seven";
        break;
      case 8:
        numberToPrint = "eight";
        break;
      case 9:
        numberToPrint = "nine";
        break;
      default:
        numberToPrint = venues.size() + "";
    }

    if (!(venues.isEmpty())) {
      MessageCli.NUMBER_VENUES.printMessage(verb, numberToPrint, suffix);
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
        if (eachVenue.getVenueCode().equals(venueCode)) {
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
