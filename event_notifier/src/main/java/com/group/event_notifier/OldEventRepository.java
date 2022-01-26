package com.group.event_notifier;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface OldEventRepository extends CrudRepository< OldEvent,String> {
  
}
