package com.cmc.repo;


import com.cmc.domain.Port;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PortRepository  extends JpaRepository<Port, Long> {
}
