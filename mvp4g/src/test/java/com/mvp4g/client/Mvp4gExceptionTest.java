package com.mvp4g.client;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class Mvp4gExceptionTest {

	@Test
	public void testConstructor() {
		String message = "test";
		Mvp4gException ex = new Mvp4gException( message );
		assertEquals( ex.getMessage(), message );
	}

}
