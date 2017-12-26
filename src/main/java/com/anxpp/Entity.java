package com.anxpp;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Getter;
import lombok.Setter;

/**
 * test
 * Created by yangtao on 2017/11/28.
 */
@JacksonXmlRootElement(localName = "xml")
@Getter
@Setter
public class Entity {
    @JacksonXmlProperty(localName = "name")
    private String name = "Tom";
    @JacksonXmlProperty(localName = "age")
    private Integer age = 18;
}
