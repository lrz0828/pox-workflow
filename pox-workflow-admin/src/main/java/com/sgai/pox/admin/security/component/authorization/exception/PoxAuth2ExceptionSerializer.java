package com.sgai.pox.admin.security.component.authorization.exception;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import lombok.SneakyThrows;

/**
 * @author pox
 */
public class PoxAuth2ExceptionSerializer extends StdSerializer<PoxAuth2Exception> {
    private static final long serialVersionUID = 1L;

    public PoxAuth2ExceptionSerializer() {
        super(PoxAuth2Exception.class);
    }

    @Override
    @SneakyThrows
    public void serialize(PoxAuth2Exception value, JsonGenerator gen, SerializerProvider provider) {
        gen.writeStartObject();
        gen.writeObjectField("code", "500");
        gen.writeStringField("msg", value.getMessage());
        gen.writeStringField("data", value.getOAuth2ErrorCode());
        gen.writeEndObject();
    }
}
