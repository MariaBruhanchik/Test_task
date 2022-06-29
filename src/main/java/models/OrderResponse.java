package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import util.PetStatus;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderResponse {
    private PetStatus available;
    private PetStatus sold;
    private PetStatus pending;
    private int status;
    private String string;
}
