package com.cmc;

import com.cmc.loader.IPortsLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;

@SpringBootApplication
public class CmcTestApplication implements ApplicationRunner {

	@Autowired
	private IPortsLoader loader;

	public static void main(String[] args) {
		SpringApplication.run(CmcTestApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		if (args.containsOption("ports-file-path")) {
			try (InputStream jsonStream = new BufferedInputStream(new FileInputStream(args.getOptionValues("ports-file-path").get(0)))){
				loader.load(jsonStream);
			}
		}
	}
}
