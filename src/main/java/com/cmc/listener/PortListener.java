package com.cmc.listener;

import com.cmc.domain.Port;
import com.cmc.event.PortEvent;
import com.cmc.services.PortService;
import com.cmc.util.PortUtil;
import com.cmc.vo.PortVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PortListener {
    @Autowired
    private PortService portService;

    @EventListener
    public void onLoadPort(PortEvent portEvent) {
        log.info("data: = " + portEvent.data());
        PortVO portVO = portEvent.data();
        String key = portEvent.key();

        Port port = PortUtil.fromVO(key, portVO);

        //check if a particular key exists in the DB
        if (!portService.exist(key)) {
            portService.add(port);
        }
    }
}
