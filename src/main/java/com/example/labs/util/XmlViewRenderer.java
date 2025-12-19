package com.example.labs.util;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.stereotype.Component;

@Component
public class XmlViewRenderer {

    private final XmlMapper xmlMapper;

    public XmlViewRenderer() {
        this.xmlMapper = new XmlMapper();
        this.xmlMapper.findAndRegisterModules();
    }

    public String render(Object model, String xslPath) throws Exception {
        String xml = xmlMapper.writeValueAsString(model);

        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<?xml-stylesheet type=\"text/xsl\" href=\"" + xslPath + "\"?>\n" +
                xml;
    }
}