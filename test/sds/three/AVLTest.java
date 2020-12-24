/*
 * This file is part of the SDSThree Open Source Project.
 * SDSThree is licensed under the GNU GPLv3.
 *
 * Copyright © 2020. Everton Bruno Silva dos Santos <evertonbrunogithub@yahoo.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package sds.three;

import java.util.Iterator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Map.Entry;

/**
 * Classe responsável por efetuar testes na árvore AVL.
 * @author Everton Bruno Silva dos Santos.
 * @version 1.4
 */
public class AVLTest {
    private AVL<Integer, String> avl;

    public AVLTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        avl = new AVL<>(Integer::compareTo);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void tamanhoDeAVLRecemCriada() {
        assertSame(0, avl.size());
    }

    @Test
    public void estadoDeAVLRecemCriada() {
        assertTrue(avl.isEmpty());
    }

    @Test
    public void limparAVLRecemCriada() {
        avl.clear();
        assertSame(0, avl.size());
        assertTrue(avl.isEmpty());
    }

    @Test
    public void inserirEmAVLRecemCriada() {
        avl.put(2020, "A");
        assertSame(1, avl.size());
        assertFalse(avl.isEmpty());
        assertEquals("A", avl.find(2020).getValue());
        assertEquals("A", avl.iterator().next().getValue());
        for (final Entry<Integer, String> entry : avl) {
            assertEquals("A", entry.getValue());
        }
        avl.forEach(entry -> {
            assertEquals("A", entry.getValue());
        });
    }

    @Test
    public void buscarEmAVLRecemCriada() {
        try {
            avl.find(2020);
            fail();
        } catch (final EntryNotFoundException valueNotFoundException) {
            assertEquals("Value not found.", valueNotFoundException.getMessage());
        }
    }

    @Test
    public void removerEmAVLRecemCriada() {
        try {
            avl.remove(2020);
            fail();
        } catch (final EntryNotFoundException valueNotFoundException) {
            assertEquals("Value not found.", valueNotFoundException.getMessage());
        } finally {
            assertSame(0, avl.size());
            assertTrue(avl.isEmpty());
        }
    }

    @Test
    public void iteradorDeAVLRecemCriada() {
        assertNull(avl.iterator().next());
        assertFalse(avl.iterator().hasNext());
    }

    @Test
    public void forEachDeAVLRecemCriada() {
        avl.forEach(entry -> {
            fail();
        });
        assertTrue(true);
    }

    @Test
    public void forInDeAVLRecemCriada() {
        for (final Entry<Integer, String> entry : avl) {
            fail();
        }
        assertTrue(true);
    }

    @Test
    public void tamanhoDeAVLAposInserir() {
        avl.put(20, "A");
        assertSame(1, avl.size());
        avl.put(23, "B");
        assertSame(2, avl.size());
        avl.put(97, "B");
        assertSame(3, avl.size());
    }

    @Test
    public void limparAVLAposInserir() {
        avl.put(20, "A");
        avl.put(23, "B");
        avl.put(97, "B");
        avl.clear();
        assertSame(0, avl.size());
        assertTrue(avl.isEmpty());
    }

    @Test
    public void estadoDeAVLAposInserir() {
        avl.put(20, "A");
        avl.put(23, "B");
        avl.put(97, "B");
        assertFalse(avl.isEmpty());
    }

    @Test
    public void inserirEmAVLAposInserirChavesDiferentes() {
        avl.put(20, "A");
        avl.put(23, "B");
        assertSame(2, avl.size());
        assertFalse(avl.isEmpty());
        assertEquals("A", avl.find(20).getValue());
        assertEquals("B", avl.find(23).getValue());
        final Iterator<Entry<Integer, String>> iterator = avl.iterator();
        assertEquals("A", iterator.next().getValue());
        assertEquals("B", iterator.next().getValue());
        final String[] array = new String[] { "A", "B" };
        int index = 0;
        for (final Entry<Integer, String> entry : avl) {
            assertEquals(array[index], entry.getValue());
            index++;
        }
    }

    @Test
    public void inserirEmAVLAposInserirChavesIguais() {
        try {
            avl.put(20, "A");
            avl.put(20, "B");
            fail();
        } catch (final KeyUsedException keyUsedException) {
            assertEquals("Key used.", keyUsedException.getMessage());
        } finally {
            assertSame(1, avl.size());
            assertFalse(avl.isEmpty());
            assertEquals("A", avl.iterator().next().getValue());
            for (final Entry<Integer, String> entry : avl) {
                assertEquals("A", entry.getValue());
            }
        }
    }

    @Test
    public void buscarEmAVLAposInserir() {
        avl.put(20, "A");
        avl.put(23, "B");
        assertEquals("A", avl.find(20).getValue());
        assertEquals("B", avl.find(23).getValue());
    }

    @Test
    public void removerDeAVLAposInserir() {
        avl.put(20, "A");
        avl.remove(20);
        assertSame(0, avl.size());
        assertTrue(avl.isEmpty());
        assertNull(avl.iterator().next());
        for (final Entry<Integer, String> entry : avl) {
            fail();
        }
        assertTrue(true);
    }

    @Test
    public void iteradorDeAVLAposInserir() {
        avl.put(20, "A");
        assertEquals("A", avl.iterator().next().getValue());
        assertSame(20, avl.iterator().next().getKey());
        assertTrue(avl.iterator().hasNext());
    }

    @Test
    public void forEachDeAVLAposInserir() {
        avl.put(20, "A");
        avl.forEach(entry -> {
            assertEquals("A", entry.getValue());
            assertSame(20, entry.getKey());
        });
    }

    @Test
    public void forInDeAVLAposInserir() {
        avl.put(20, "A");
        for (final Entry<Integer, String> entry : avl) {
            assertEquals("A", entry.getValue());
            assertSame(20, entry.getKey());
        }
    }

    @Test
    public void alterarValorDeFolha() {
        avl.put(20, "A");
        assertEquals("A", avl.iterator().next().setValue("B"));
        assertEquals("B", avl.iterator().next().getValue());
        assertEquals("B", avl.find(20).getValue());
        for(final Entry<Integer, String> entry : avl) {
            assertEquals("B", entry.getValue());
        }
        avl.forEach(entry -> {
            assertEquals("B", entry.getValue());
        });
    }

}
