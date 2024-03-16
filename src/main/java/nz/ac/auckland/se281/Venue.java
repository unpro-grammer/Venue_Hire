package nz.ac.auckland.se281;

public class Venue {
  private String venueName;
  private String venueCode;
  private String capacityInput;
  private String hirefeeInput;

  public Venue(String venueName, String venueCode, String capacityInput, String hireFeeInput) {
    this.venueName = venueName;
    this.venueCode = venueCode;
    this.capacityInput = capacityInput;
    this.hirefeeInput = hireFeeInput;
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
    return hirefeeInput;
  }
}
