package com.group.event_notifier;




import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class OldEvent {
  @Id
    private Long id;
    private String eventName;
    private String type1;
    private String type2;
    private String type3;
    private String type4;
    private String type5;
    private String eventDescription;
    private String endingDate;
    private String location;
    private String imgDirectory;
    private Long organizationId;

}

