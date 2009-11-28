package com.mvp4g.util.config.loader.annotation;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.google.gwt.core.ext.typeinfo.JClassType;
import com.mvp4g.util.config.Mvp4gConfiguration;
import com.mvp4g.util.config.element.SimpleMvp4gElement;
import com.mvp4g.util.exception.loader.Mvp4gAnnotationException;
import com.mvp4g.util.test_tools.TypeOracleStub;

public abstract class AbstractMvp4gAnnotationLoaderTest<A extends Annotation, L extends Mvp4gAnnotationsLoader<A>> {

	protected Mvp4gConfiguration configuration = null;
	protected TypeOracleStub oracle = null;
	protected L loader = null;

	@Before
	public void setUp() {
		oracle = new TypeOracleStub();
		configuration = new Mvp4gConfiguration( null, oracle );
		loader = createLoader();
	}

	@Test( expected = Mvp4gAnnotationException.class )
	public void testWrongClass() throws Mvp4gAnnotationException {
		try {
			List<JClassType> annotedClasses = new ArrayList<JClassType>();
			annotedClasses.add( oracle.addClass( Object.class ) );
			loader.load( annotedClasses, configuration );
		} catch ( Mvp4gAnnotationException e ) {
			assertTrue( e.getMessage().contains( "this class must implement " ) );
			throw e;
		}
	}

	@Test( expected = Mvp4gAnnotationException.class )
	public void testSameElementTwice() throws Mvp4gAnnotationException {
		try {
			List<JClassType> annotedClasses = new ArrayList<JClassType>();
			JClassType type = oracle.addClass( getSimpleClass() );
			annotedClasses.add( type );
			annotedClasses.add( type );
			loader.load( annotedClasses, configuration );
		} catch ( Mvp4gAnnotationException e ) {
			assertTrue( e.getMessage().contains( "Duplicate" ) );
			throw e;
		}
	}

	@Test
	public void testSimpleElement() throws Mvp4gAnnotationException {
		List<JClassType> annotedClasses = new ArrayList<JClassType>();
		annotedClasses.add( oracle.addClass( getSimpleClass() ) );
		Set<SimpleMvp4gElement> elements = getSet();
		assertEquals( elements.size(), 0 );
		loader.load( annotedClasses, configuration );
		assertEquals( elements.size(), 1 );
		SimpleMvp4gElement element = elements.iterator().next();
		assertEquals( element.getName(), element.getClassName().replace( '.', '_' ) );
	}

	@Test
	public void testElementWithName() throws Mvp4gAnnotationException {
		List<JClassType> annotedClasses = new ArrayList<JClassType>();
		annotedClasses.add( oracle.addClass( getWithNameClass() ) );
		Set<SimpleMvp4gElement> elements = getSet();
		assertEquals( elements.size(), 0 );
		loader.load( annotedClasses, configuration );
		assertEquals( elements.size(), 1 );
		SimpleMvp4gElement element = elements.iterator().next();
		assertEquals( element.getName(), "name" );
	}

	abstract protected L createLoader();

	abstract protected <T extends SimpleMvp4gElement> Set<T> getSet();

	abstract protected Class<?> getSimpleClass();

	abstract protected Class<?> getWithNameClass();

}