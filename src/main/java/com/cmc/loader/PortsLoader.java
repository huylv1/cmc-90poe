package com.cmc.loader;

import com.cmc.event.PortEvent;
import com.cmc.exception.DataLoadException;
import com.cmc.listener.PortListener;
import com.cmc.vo.PortVO;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

@Component
@Slf4j
public class PortsLoader implements IPortsLoader {
    @Autowired
    private PortListener portListener;

    public void load(InputStream jsonStream) {
        try {
            //Read Json file
            JsonParser jsonParser = new JsonFactory().createParser(jsonStream);
            parseJSON(jsonParser);
        } catch (Exception e) {
            throw new DataLoadException("Error while loading data", e);
        }
    }

    private void parseJSON(JsonParser jsonParser) throws IOException {

        //skip the first token
        jsonParser.nextToken();

        //loop through every map object
        while(jsonParser.nextToken() != JsonToken.END_OBJECT) {
            // key
            String key = jsonParser.getCurrentName();
            log.info("Port's key = {}", key);

            //port object
            PortVO portVO = readPort(jsonParser);

            //trigger event
            portListener.onLoadPort(new PortEvent(key, portVO));
        }
    }

    private PortVO readPort(JsonParser jsonParser) throws IOException {
        log.info("Reading a port");
        PortVO vo = new PortVO();
        while(jsonParser.nextToken() != JsonToken.END_OBJECT){
            String name = jsonParser.getCurrentName();
            if("name".equals(name)) {
                jsonParser.nextToken();
                vo.setName(jsonParser.getText());

            } else if("city".equals(name)){
                jsonParser.nextToken();
                vo.setCity(jsonParser.getText());

            } else if("country".equals(name)){
                jsonParser.nextToken();
                vo.setCountry(jsonParser.getText());

            } else if("alias".equals(name)){
                jsonParser.nextToken();
                ArrayList<String> alias = new ArrayList<>();
                while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
                    alias.add(jsonParser.getText());
                }

                vo.setAlias(alias);
            } else if("regions".equals(name)){
                jsonParser.nextToken();
                ArrayList<String> regions = new ArrayList<>();
                while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
                    regions.add(jsonParser.getText());
                }

                vo.setRegions(regions);
            } else if("coordinates".equals(name)){
                jsonParser.nextToken();
                ArrayList<Double> coordinates = new ArrayList<>();
                while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
                    coordinates.add(jsonParser.getDoubleValue());
                }

                vo.setCoordinates(coordinates);
            } else if("province".equals(name)){
                jsonParser.nextToken();
                vo.setProvince(jsonParser.getText());

            } else if("timezone".equals(name)){
                jsonParser.nextToken();
                vo.setTimezone(jsonParser.getText());

            } else if("unlocs".equals(name)){
                jsonParser.nextToken();
                ArrayList<String> unlocs = new ArrayList<>();
                while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
                    unlocs.add(jsonParser.getText());
                }

                vo.setUnlocs(unlocs);
            } else if("code".equals(name)){
                jsonParser.nextToken();
                vo.setCode(jsonParser.getText());
            }
        }

        log.info("Finished loading port {}", vo);
        return vo;
    }
}
