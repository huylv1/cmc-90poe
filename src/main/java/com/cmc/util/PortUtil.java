package com.cmc.util;

import com.cmc.CmcTestApplication;
import com.cmc.domain.Coordinate;
import com.cmc.domain.Port;
import com.cmc.exception.DataLoadException;
import com.cmc.services.PortService;
import com.cmc.vo.PortVO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class PortUtil {
    public static Port fromVO(PortVO portVO) {
        Port port = new Port();
        updatePort(port, portVO);
        return port;
    }

    public static void updatePort(Port port, PortVO portVO) {
        port.setCode(portVO.getCode());
        port.setName(portVO.getName());
        port.setCity(portVO.getCity());
        port.setCountry(portVO.getCountry());
        port.setProvince(portVO.getProvince());
        port.setTimezone(portVO.getTimezone());

        List<String> alias = portVO.getAlias();
        Optional.ofNullable(alias).ifPresent(strings -> {
            String result = String.join(", ", strings);
            port.setAlias(result);
        });

        List<String> regions = portVO.getAlias();
        Optional.ofNullable(regions).ifPresent(strings -> {
            String result = String.join(", ", strings);
            port.setRegion(result);
        });

        List<String> unlocs = portVO.getAlias();
        Optional.ofNullable(unlocs).ifPresent(strings -> {
            String result = String.join(", ", strings);
            port.setUnloc(result);
        });

        List<Double> coordinates = portVO.getCoordinates();

        Optional.ofNullable(coordinates).ifPresent(coordinatess -> {
            if (!coordinates.isEmpty() && coordinatess.size() == 2) {
                Double lon = coordinatess.get(0);
                Double lat = coordinatess.get(1);

                Coordinate coordinate = new Coordinate();
                coordinate.setLatitude(lat);
                coordinate.setLongitude(lon);
                port.setCoordinate(coordinate);
            }
        });
    }

    public static void loadPort(PortService portService) {
        try {
            //Read Json file
            ObjectMapper mapper = new ObjectMapper();

            InputStream jsonStream = CmcTestApplication.class.getResourceAsStream("/ports.json");
            Map<String, PortVO> portVOMap = mapper.readValue(jsonStream, new TypeReference<Map<String, PortVO>>() {});

            for(Map.Entry<String, PortVO> entry: portVOMap.entrySet()) {
                PortVO portVO = entry.getValue();
                Port port = PortUtil.fromVO(portVO);
                portService.add(port);
            }
        } catch (Exception e) {
            throw new DataLoadException("Error while loading data", e);
        }

    }
}
