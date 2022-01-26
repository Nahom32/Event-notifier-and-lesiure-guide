package com.group.event_notifier.security;

import org.springframework.data.repository.CrudRepository;

public interface AdminRepository extends CrudRepository<Admin, Long> {
    public Admin findByUsername(String username);
}
