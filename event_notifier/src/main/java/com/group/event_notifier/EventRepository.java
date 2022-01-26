package com.group.event_notifier;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface EventRepository extends CrudRepository< Event, Long>{
  

  @Query("SELECT e FROM Event e WHERE e.eventName LIKE %?1%"
            + " OR e.type1 LIKE %?1%"
            + " OR e.eventDescription LIKE %?1%"
            + " OR e.type2 LIKE %?1%"
            + " OR CONCAT(e.endingDate, '') LIKE %?1%")
public List<Event> search(String keyword);
}
