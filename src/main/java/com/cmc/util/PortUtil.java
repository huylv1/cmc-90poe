package com.cmc.util;

import com.cmc.domain.Coordinate;
import com.cmc.domain.Port;
import com.cmc.vo.PortVO;

import java.util.List;
import java.util.Optional;

public class PortUtil {
    public static Port fromVO(String key, PortVO portVO) {
        Port port = new Port();
        port.setPortKey(key);
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

}
