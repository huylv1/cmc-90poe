package com.cmc;

import com.cmc.services.PortService;
import com.cmc.util.PortUtil;
import com.cmc.vo.PortVO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.InputStream;
import java.util.Map;

@SpringBootApplication
public class CmcTestApplication implements CommandLineRunner {

	@Autowired
	private PortService portService;

	public static void main(String[] args) {
		SpringApplication.run(CmcTestApplication.class, args);
	}

	@Override
	public void run(String... args) {
		PortUtil.loadPort(portService);
	}
}
