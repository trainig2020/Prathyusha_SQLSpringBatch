package com.prathyusha.batch;

import org.springframework.batch.item.file.LineMapper;
import org.springframework.stereotype.Component;

public class PassThroughLineMapper implements LineMapper<String> {

	 @Override
	    public String mapLine(String line, int lineNumber) throws Exception {
	        return line;
	    }
}
