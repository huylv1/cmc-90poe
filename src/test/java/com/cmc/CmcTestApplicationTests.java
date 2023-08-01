package com.cmc;

import com.cmc.domain.Port;
import com.cmc.loader.IPortsLoader;
import com.cmc.services.PortService;
import com.cmc.util.PortUtil;
import com.cmc.vo.PortVO;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Transactional
class CmcTestApplicationTests {

	@Autowired
	private PortService portService;

	@Autowired
	private IPortsLoader portsLoader;

	@Test
	void contextLoads() {
		assertNotNull(portService);
		assertNotNull(portsLoader);
	}

	@Test
	void testLoadPorts() throws IOException {
		try(InputStream jsonStream = CmcTestApplication.class.getResourceAsStream("/ports-test.json")) {
			portsLoader.load(jsonStream);
		}

		assertEquals(2, portService.findAll().size());
    }

	@Test
	void testAdd() {
		PortVO portVO = new PortVO();
		String key = "ABC";
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

		Port port = PortUtil.fromVO(key, portVO);
		portService.add(port);

		assertEquals(1, portService.findAll().size());
	}

	@Test
	void testUpdate() {

		PortVO portVO = new PortVO();
		String key = "ABC";
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

		Port port = PortUtil.fromVO(key, portVO);
		portService.add(port);

		String newName = "Abc xyz";
		portVO.setName(newName);
		portService.update(portVO, key);

		Port updatedPort = portService.findPort(key);
		assertEquals(newName, updatedPort.getName());
	}
}
