package com.dub;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

import org.junit.Test;

import com.dub.site.minimumSpanningTree.UWEdge;
import com.dub.util.SimpleList;
import com.dub.util.SimpleListIterator;

public class ListTestJunit {

	@Test
	public void testAdd() {
		
		SimpleList<String> list = new SimpleList<>();
		
		assertTrue(list.isEmpty());
		
		assertTrue(list.add("sator"));
		
		assertFalse(list.isEmpty());
		
		System.out.println(list.add("arepo"));
		System.out.println(list.add("tenet"));
		
		Object[] array = list.toArray();
		assertEquals(3, array.length);
		
		assertTrue(list.contains("sator"));
		assertFalse(list.contains("opera"));
		
		list.add("opera");
		list.add("rotas");
		
		array = list.toArray();
		
		assertEquals(0, list.indexOf("sator"));
		assertEquals(2, list.indexOf("tenet"));
		assertEquals(4, list.indexOf("rotas"));
		assertEquals(-1, list.indexOf("grunge"));
		
		assertEquals("sator", list.get(0));
		assertEquals("tenet", list.get(2));
		assertEquals("rotas", list.get(4));
		assertEquals(null, list.get(5));
		
		list.remove("sator");
		array = list.toArray();
		
		assertEquals("arepo", array[0]);
		
		list.remove("rotas");
		
		array = list.toArray();
		assertEquals("tenet", array[1]);
		
		list.remove("tenet");
		list.remove("opera");
		list.remove("arepo");
		
		assertTrue(list.isEmpty());
		
		list.add("AA");
		assertFalse(list.isEmpty());
		list.add("BB");
		list.add("CC");
		
		ListIterator<String> it = new SimpleListIterator<>(list);
		assertTrue(it.hasNext());
		assertEquals("AA", it.next());
		assertEquals("BB", it.next());
		assertTrue(it.hasNext());
		assertEquals("CC", it.next());
		assertFalse(it.hasNext());
		
		it = new SimpleListIterator<>(list);
		while (it.hasNext()) {
			it.next();
		}
		
		assertTrue(it.hasPrevious());
		assertEquals("BB", it.previous());
		assertTrue(it.hasPrevious());
		assertEquals("AA", it.previous());
		assertFalse(it.hasPrevious());
		assertFalse(list.isEmpty());
		
		list.clear();
		assertTrue(list.isEmpty());
		
		list.add("modus");
		assertFalse(list.isEmpty());
		
		list.clear();
		
		list.add("sator");
		list.add("arepo");
		list.add("tenet");
		list.add("opera");	
		list.add("rotas");
		
		assertEquals("sator", list.remove(0));
		assertEquals("rotas", list.remove(3));
		assertEquals("tenet", list.remove(1));
		
		assertEquals(2, list.size());
		
		


	}

}
