package models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import util.PetStatus;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pet {
    private String name;
    private long id;
    private PetStatus status;
    private Category createCategory;
    private List<Tag> tags;
    private List<PhotoUrls> photoUrls;
}

