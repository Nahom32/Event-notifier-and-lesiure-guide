package com.group.event_notifier;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
   @NotNull(message = "you must enter the name of the event")
    private String eventName;
   @NotNull(message= "you must your first type ")
    private String type1;
   @NotNull(message= "you must enter your second type")
    private String type2;
    
    private String type3;
    private String type4;
    private String type5;
    @NotNull(message= "you must enter a description to be displayed")
    private String eventDescription;

    private String startDate;
    private String endingDate;
    //@NotNull(message= "you must enter location")
    private String location;
    @NotNull(message= "you must register a file")
    private String imgDirectory;
    private Long organizationId;

}