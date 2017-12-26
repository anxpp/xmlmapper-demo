package com.anxpp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Created by yangtao on 2017/11/28.
 */
@Component
@Slf4j
public class Test {

    public void print() throws JsonProcessingException {
        log.info(new XmlMapper().writeValueAsString(new Entity()));
    }
}
