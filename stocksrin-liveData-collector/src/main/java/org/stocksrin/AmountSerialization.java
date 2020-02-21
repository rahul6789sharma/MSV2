package org.stocksrin;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class AmountSerialization extends StdSerializer<Double> {

	private static final long serialVersionUID = 1L;

	protected AmountSerialization(Class<Double> t) {
		super(t);
	}

	public AmountSerialization() {
		this(Double.class);
	}

	@Override
	public void serialize(Double value, JsonGenerator gen, SerializerProvider arg2) throws IOException {
		BigDecimal amount = BigDecimal.valueOf(value).setScale(2, RoundingMode.HALF_UP);
		//gen.writeString(String.valueOf(amount));
		gen.writeNumber(amount);
	}

}
