package de.hartz.software.sodevsalaryguide.application.http.api.helper;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import de.hartz.software.sodevsalaryguide.core.model.Range;
import lombok.extern.log4j.Log4j2;
import lombok.val;

import java.io.IOException;

@Log4j2
public class RangeDeserializer extends StdDeserializer<Range> {

    public RangeDeserializer() {
        super(Range.class);
    }

    @Override
    public Range deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException {
        if (jsonParser.currentToken() == JsonToken.START_ARRAY) {
            jsonParser.nextToken(); // array token
            jsonParser.nextToken();
            val first = jsonParser.getValueAsInt();
            jsonParser.nextToken();
            val second = jsonParser.getValueAsInt();
            // TODO: For some reason the Max and min are switched.. Overall better to use object with proper names..
            return new Range(second, first);
        }
        return null;
    }

}