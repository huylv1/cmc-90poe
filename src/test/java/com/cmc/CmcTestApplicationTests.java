package com.cmc;

import com.cmc.domain.Coordinate;
import com.cmc.domain.Port;
import com.cmc.services.PortService;
import com.cmc.util.PortUtil;
import com.cmc.vo.PortVO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
class CmcTestApplicationTests {

	@Autowired
	private PortService portService;

	@Test
	void contextLoads() {
		Assertions.assertNotNull(portService);
		PortUtil.loadPort(portService);
	}

	@Test
	void testAdd() {
		PortVO portVO = new PortVO();
		portVO.setCode("40928");
		portVO.setName("Svendborg");
		portVO.setCity("Svendborg");
		portVO.setCountry("Denmark");
		portVO.setProvince("Region Syddanmark");
		portVO.setTimezone("Europe/Copenhagen");

		ArrayList<Double> coordinates = new ArrayList<>();
		coordinates.add(55.067434d);
		coordinates.add(10.607282d);
		portVO.setCoordinates(coordinates);

		Port port = PortUtil.fromVO(portVO);
		portService.add(port);
	}

	@Test
	void testUpdate() {

		PortVO portVO = new PortVO();
		portVO.setCode("40928");
		portVO.setName("Svendborg");
		portVO.setCity("Svendborg");
		portVO.setCountry("Denmark");
		portVO.setProvince("Region Syddanmark");
		portVO.setTimezone("Europe/Copenhagen");

		ArrayList<Double> coordinates = new ArrayList<>();
		coordinates.add(55.067434d);
		coordinates.add(10.607282d);
		portVO.setCoordinates(coordinates);

		Port port = PortUtil.fromVO(portVO);
		Long id = portService.add(port);

		String newName = "Abc xyz";
		portVO.setName(newName);
		portService.update(portVO, id);

		Port updatedPort = portService.findPort(id);
		System.out.println(updatedPort);
		Assertions.assertEquals(newName, updatedPort.getName());
	}
}
