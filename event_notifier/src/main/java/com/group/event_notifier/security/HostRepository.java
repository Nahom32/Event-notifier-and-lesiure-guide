package com.group.event_notifier.security;

import org.springframework.data.repository.CrudRepository;

public interface HostRepository extends CrudRepository<Host, Long> {
    public Host findByUsername(String username);

}
