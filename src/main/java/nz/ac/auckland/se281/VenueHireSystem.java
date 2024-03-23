package nz.ac.auckland.se281;

import java.util.ArrayList;
import nz.ac.auckland.se281.Types.CateringType;
import nz.ac.auckland.se281.Types.FloralType;

public class VenueHireSystem {

  private ArrayList<Venue> venues = new ArrayList<>();
  private ArrayList<String> allVenueCodes = new ArrayList<>();

  private String systemDate = "";

  public VenueHireSystem() {}

  public void printVenues() {
    String numberToPrint = null;
    String verb = "are"; // are for plural, is for singular
    String suffix = "s"; // s for plural, "" for singular

    // convert single-digit numbers to word form
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
        numberToPrint = venues.size() + ""; // 10 or greater represented numerically
    }

    // list out all venues and their details given at least one is registered
    if (!(venues.isEmpty())) {
      MessageCli.NUMBER_VENUES.printMessage(verb, numberToPrint, suffix);
      for (Venue eachVenue : venues) {
        MessageCli.VENUE_ENTRY.printMessage(
            eachVenue.getVenueName(),
            eachVenue.getVenueCode(),
            eachVenue.getCapacityInput(),
            eachVenue.getHireFeeInput());
      }
    }
  }

  private boolean isInteger(String number) {
    try {
      Integer.parseInt(number);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  private boolean isPositive(String number) {
    int num = Integer.parseInt(number);
    if (num > 0) {
      return true;
    }
    return false;
  }

  public void createVenue(
      String venueName, String venueCode, String capacityInput, String hireFeeInput) {

    boolean validVenueCreation = true;

    // check for invalid arguments
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

      // check whether capacity or hire fee is an integer
    } else if (!(isInteger(capacityInput))) {
      MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("capacity", "");
      validVenueCreation = false;
    } else if (!(isInteger(hireFeeInput))) {
      MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("hire fee", "");
      validVenueCreation = false;

      // check whether an integer value for capacity or hire fee is positive (short circuit)
    } else if ((isInteger(capacityInput)) && !isPositive(capacityInput)) {
      MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("capacity", " positive");
      validVenueCreation = false;
    } else if ((isInteger(hireFeeInput)) && !isPositive(hireFeeInput)) {
      MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("hire fee", " positive");
      validVenueCreation = false;
    }

    // add valid venue creations to venues list, and its code to list of unique venue codes
    if (validVenueCreation) {
      Venue currentVenue = new Venue(venueName, venueCode, capacityInput, hireFeeInput);
      venues.add(currentVenue);
      allVenueCodes.add(venueCode);
      MessageCli.VENUE_SUCCESSFULLY_CREATED.printMessage(venueName, venueCode);
    }
  }

  public void setSystemDate(String dateInput) {
    MessageCli.DATE_SET.printMessage(dateInput);
    this.systemDate = dateInput;
  }

  public void printSystemDate() {
    if (systemDate.isEmpty()) {
      System.out.println("Current system date is not set.");
    } else {
      MessageCli.CURRENT_DATE.printMessage(systemDate);
    }
  }

  public void makeBooking(String[] options) {

    if (systemDate.isEmpty()) {
      MessageCli.BOOKING_NOT_MADE_DATE_NOT_SET.printMessage();
    } else if (venues.isEmpty()) {
      MessageCli.BOOKING_NOT_MADE_NO_VENUES.printMessage();
    } else if (!(allVenueCodes.contains(options[0]))) {
      MessageCli.BOOKING_NOT_MADE_VENUE_NOT_FOUND.printMessage(options[0]);
    }
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
