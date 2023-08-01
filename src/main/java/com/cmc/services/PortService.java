package com.cmc.services;

import com.cmc.domain.Port;
import com.cmc.exception.PortException;
import com.cmc.repo.PortRepository;
import com.cmc.util.PortUtil;
import com.cmc.vo.PortVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PortService {
    @Autowired
    private PortRepository portRepository;

    public void add(Port port) {
        log.info("Inserting port: " + port );
        port = portRepository.save(port);
        log.info("Saved port: " + port );
    }

    public void update(PortVO portVO, String key) {
        Optional<Port> data = portRepository.findById(key);
        if (data.isPresent()) {
            Port _port = data.get();
            log.info("Data found : " + _port);

            PortUtil.updatePort(_port, portVO);
            log.info("Updated data : " + _port);
            
            portRepository.save(_port);
        } else {
            throw new PortException("Port not found");
        }
    }

    public Port findPort(String key) {
        Optional<Port> data = portRepository.findById(key);
        if (data.isEmpty()) {
            throw new PortException("Port not found");
        }

        return data.get();
    }

    public boolean exist(String key) {
        Optional<Port> data = portRepository.findById(key);
        return data.isPresent();
    }

    public List<Port> findAll() {
        return portRepository.findAll();
    }
}
