/**
 * 
 */
package com.github.podd.ontology.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.github.podd.ontologies.PODDBASE;
import com.github.podd.ontologies.PODDSCIENCE;

/**
 * @author Peter Ansell p_ansell@yahoo.com
 *
 */
public class PoddVocabBuilderTest {
	@Test
	public final void testPoddBase() {
		assertEquals("http://purl.org/podd/ns/poddBase#artifactHasTopObject",
				PODDBASE.ARTIFACT_HAS_TOP_OBJECT.toString());
	}

	@Test
	public final void testPoddScience() {
		assertEquals("http://purl.org/podd/ns/poddScience#Tray",
				PODDSCIENCE.TRAY.toString());
	}

}
