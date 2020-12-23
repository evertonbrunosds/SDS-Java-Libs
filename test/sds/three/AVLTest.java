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
import sds.three.AVL.Leaf;

/**
 * Classe responsável por efetuar testes na árvore AVL.
 * @author Everton Bruno Silva dos Santos.
 * @version 1.0
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
        assertEquals("A", avl.find(2020));
        assertEquals("A", avl.iterator().next().getValue());
        for (final Leaf<Integer, String> leaf : avl) {
            assertEquals("A", leaf.getValue());
        }
        avl.forEach(leaf -> {
            assertEquals("A", leaf.getValue());
        });
    }

    @Test
    public void buscarEmAVLRecemCriada() {
        try {
            avl.find(2020);
            fail();
        } catch (final ValueNotFoundException valueNotFoundException) {
            assertEquals("Value not found.", valueNotFoundException.getMessage());
        }
    }

    @Test
    public void removerEmAVLRecemCriada() {
        try {
            avl.remove(2020);
            fail();
        } catch (final ValueNotFoundException valueNotFoundException) {
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
        avl.forEach(leaf -> {
            fail();
        });
        assertTrue(true);
    }

    @Test
    public void forInDeAVLRecemCriada() {
        for (final Leaf<Integer, String> leaf : avl) {
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
        assertEquals("A", avl.find(20));
        assertEquals("B", avl.find(23));
        final Iterator<Leaf<Integer, String>> iterator = avl.iterator();
        assertEquals("A", iterator.next().getValue());
        assertEquals("B", iterator.next().getValue());
        final String[] array = new String[] { "A", "B" };
        int index = 0;
        for (final Leaf<Integer, String> leaf : avl) {
            assertEquals(array[index], leaf.getValue());
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
            for (final Leaf<Integer, String> leaf : avl) {
                assertEquals("A", leaf.getValue());
            }
        }
    }

    @Test
    public void buscarEmAVLAposInserir() {
        avl.put(20, "A");
        avl.put(23, "B");
        assertEquals("A", avl.find(20));
        assertEquals("B", avl.find(23));
    }

    @Test
    public void removerDeAVLAposInserir() {
        avl.put(20, "A");
        avl.remove(20);
        assertSame(0, avl.size());
        assertTrue(avl.isEmpty());
        assertNull(avl.iterator().next());
        for (final Leaf<Integer, String> leaf : avl) {
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
        avl.forEach(leaf -> {
            assertEquals("A", leaf.getValue());
            assertSame(20, leaf.getKey());
        });
    }

    @Test
    public void forInDeAVLAposInserir() {
        avl.put(20, "A");
        for (final Leaf<Integer, String> leaf : avl) {
            assertEquals("A", leaf.getValue());
            assertSame(20, leaf.getKey());
        }
    }

}
