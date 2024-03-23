package nz.ac.auckland.se281;

import java.util.ArrayList;
import nz.ac.auckland.se281.Types.CateringType;
import nz.ac.auckland.se281.Types.FloralType;

public class VenueHireSystem {

  private ArrayList<Venue> venues = new ArrayList<>();
  private ArrayList<String> allVenueCodes = new ArrayList<>();
  private ArrayList<Booking> bookings = new ArrayList<>();

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
            eachVenue.getHireFeeInput(),
            eachVenue.getNextAvailableDate());
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
      Venue currentVenue = new Venue(venueName, venueCode, capacityInput, hireFeeInput, systemDate);
      venues.add(currentVenue);
      allVenueCodes.add(venueCode);
      MessageCli.VENUE_SUCCESSFULLY_CREATED.printMessage(venueName, venueCode);
    }
  }

  public void setSystemDate(String dateInput) {
    MessageCli.DATE_SET.printMessage(dateInput);
    this.systemDate = dateInput;

    // ensure next available dates of all venues aren't in the past
    for (Venue venue : venues) {
      updateNextAvailableDate(venue);
    }
  }

  public void printSystemDate() {
    if (systemDate.isEmpty()) {
      System.out.println("Current system date is not set.");
    } else {
      MessageCli.CURRENT_DATE.printMessage(systemDate);
    }
  }

  private boolean bookingIsInFuture(String systemDate, String bookingDate) {

    // organise components of systemDate
    String[] systemDateParts = systemDate.split("/");
    int sysDay = Integer.parseInt(systemDateParts[0]);
    int sysMonth = Integer.parseInt(systemDateParts[1]);
    int sysYear = Integer.parseInt(systemDateParts[2]);

    // organise components of bookingDate
    String[] bookingDateParts = bookingDate.split("/");
    int bookingDay = Integer.parseInt(bookingDateParts[0]);
    int bookingMonth = Integer.parseInt(bookingDateParts[1]);
    int bookingYear = Integer.parseInt(bookingDateParts[2]);

    // check that booking date is today or beyond
    if (bookingYear < sysYear) {
      return false;
    } else if ((bookingYear == sysYear) && (bookingMonth < sysMonth)) {
      return false;
    } else if ((bookingYear == sysYear) && (bookingMonth == sysMonth) && (bookingDay < sysDay)) {
      return false;
    }

    return true;
  }

  private boolean venueAvailableThen(String venueCode, String bookingDate) {
    for (Booking booking : bookings) {
      if (booking.getVenueCodeofBooking().equals(venueCode)
          && booking.getDate().equals(bookingDate)) {
        String venueName = booking.getVenueNameofBooking();
        MessageCli.BOOKING_NOT_MADE_VENUE_ALREADY_BOOKED.printMessage(venueName, bookingDate);
        return false;
      }
    }
    return true;
  }

  private String adjustAttendeesCount(String attendeesCount, String venueCapacity) {
    int attendeesCountInt = Integer.parseInt(attendeesCount);
    int venueCapInt = Integer.parseInt(venueCapacity);

    int lowerlimit = venueCapInt / 4; // minimum 25% of spaces occupied

    if (attendeesCountInt < lowerlimit) {
      attendeesCountInt = lowerlimit;
    } else if (attendeesCountInt > venueCapInt) {
      attendeesCountInt = venueCapInt;
    }

    // print notice if number of attendees has changed from input
    if (!(Integer.parseInt(attendeesCount) == attendeesCountInt)) {
      MessageCli.BOOKING_ATTENDEES_ADJUSTED.printMessage(
          attendeesCount, (attendeesCountInt + ""), venueCapacity);
    }

    return attendeesCountInt + "";
  }

  public void updateNextAvailableDate(Venue venue) {
    venue.setNextAvailableDate(systemDate);
    // the next available date will increment from current system date until no bookings already
    // exist on a given day
    boolean currentNotAvailable = false;
    for (Booking booking : bookings) {
      if (booking.getDate().equals(systemDate)) {
        currentNotAvailable = true;
      }
    }

    String dayComponent = (systemDate.split("/"))[0];
    String month = (systemDate.split("/"))[1];
    String year = (systemDate.split("/"))[2];
    int day = Integer.parseInt(dayComponent);

    String newDate = systemDate;

    while (currentNotAvailable) {
      currentNotAvailable = false;
      day += 1;
      dayComponent = day + "";
      if (day < 10) {
        dayComponent = "0" + dayComponent; // pad single digits with leading 0
      }
      newDate = dayComponent + "/" + month + "/" + year;
      for (Booking booking : bookings) {
        if (booking.getDate().equals(newDate)) {
          currentNotAvailable = true;
        }
      }
    }

    venue.setNextAvailableDate(newDate);
  }

  public void makeBooking(String[] options) {

    // check that the booking proposed to be made is not invalid.
    String venueCode = options[0];
    String bookingDate = options[1];
    String customerEmail = options[2];
    String attendeesCount = options[3];

    if (systemDate.isEmpty()) {
      MessageCli.BOOKING_NOT_MADE_DATE_NOT_SET.printMessage();
    } else if (venues.isEmpty()) {
      MessageCli.BOOKING_NOT_MADE_NO_VENUES.printMessage();
    } else if (!(allVenueCodes.contains(venueCode))) {
      MessageCli.BOOKING_NOT_MADE_VENUE_NOT_FOUND.printMessage(venueCode);
    } else if (!(bookingIsInFuture(systemDate, bookingDate))) {
      MessageCli.BOOKING_NOT_MADE_PAST_DATE.printMessage(bookingDate, systemDate);
    } else if (!(venueAvailableThen(venueCode, bookingDate))) {
      // no statements as the function in the expression already executes
    } else { // successful booking
      String bookingRef = BookingReferenceGenerator.generateBookingReference();

      Venue venueToBook = null;
      for (Venue venue : venues) {
        if (venue.getVenueCode().equals(venueCode)) {
          venueToBook = venue;
        }
      }

      attendeesCount = adjustAttendeesCount(attendeesCount, venueToBook.getCapacityInput());

      Booking booking =
          new Booking(venueToBook, bookingDate, customerEmail, attendeesCount, bookingRef);
      bookings.add(booking);
      venueToBook.addBookingInstance(booking);
      MessageCli.MAKE_BOOKING_SUCCESSFUL.printMessage(
          bookingRef, venueToBook.getVenueName(), bookingDate, attendeesCount);
      updateNextAvailableDate(venueToBook);
    }
  }

  public void printBookings(String venueCode) {

    Venue venueOfInterest = null;

    if (venues.isEmpty() || !(allVenueCodes.contains(venueCode))) {
      MessageCli.PRINT_BOOKINGS_VENUE_NOT_FOUND.printMessage(venueCode);
    } else {
      for (Venue venue : venues) {
        if (venueCode.equals(venue.getVenueCode())) {
          venueOfInterest = venue;
        }
      }

      // print bookings (or lack thereof) of the given venue
      MessageCli.PRINT_BOOKINGS_HEADER.printMessage(venueOfInterest.getVenueName());
      if (venueOfInterest.getBookingHistory().isEmpty()) {
        MessageCli.PRINT_BOOKINGS_NONE.printMessage(venueOfInterest.getVenueName());
      } else {
        for (Booking booking : venueOfInterest.getBookingHistory()) {
          MessageCli.PRINT_BOOKINGS_ENTRY.printMessage(booking.getReference(), booking.getDate());
        }
      }
    }
  }

  private boolean bookingRefExists(String bookingReference) {
    for (Booking booking : bookings) {
      if (booking.getReference().equals(bookingReference)) {
        return true;
      }
    }
    return false;
  }

  public void addCateringService(String bookingReference, CateringType cateringType) {

    if (!(bookingRefExists(bookingReference))) {
      MessageCli.SERVICE_NOT_ADDED_BOOKING_NOT_FOUND.printMessage("Catering", bookingReference);
    } else {
      CateringService catering =
          new CateringService(cateringType.getCostPerPerson(), cateringType.getName());
    }
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
