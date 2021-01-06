/*
 * This file is part of the SDSUtilityLib Open Source Project.
 * SDSUtilityLib is licensed under the GNU GPLv3.
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
package evertonbrunosds.SDSUtilityLib.v1.api;

import evertonbrunosds.SDSUtilityLib.v1.exceptions.InvalidDoubleException;
import evertonbrunosds.SDSUtilityLib.v1.exceptions.InvalidIntegerException;
import evertonbrunosds.SDSUtilityLib.v1.exceptions.InvalidStringException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Classe responsável por efetuar testes no filtro.
 * @author Everton Bruno Silva dos Santos.
 * @version 1.0
 * @since 1.0
 */
public class FilterTest {

    public FilterTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void validStringTester() {
        try {
            Filter.String.invalid("valid string");
            assertTrue(true);
        } catch (final InvalidStringException ex) {
            fail();
        }
    }

    @Test
    public void invalidNullString() {
        try {
            Filter.String.invalid(null);
            fail();
        } catch (final InvalidStringException ex) {
            assertEquals("Invalid string.", ex.getMessage());
        }
    }

    @Test
    public void invalidEmptyString() {
        try {
            Filter.String.invalid("");
            fail();
        } catch (final InvalidStringException ex) {
            assertEquals("Invalid string.", ex.getMessage());
        }
    }

    @Test
    public void validPositiveInteger() {
        try {
            Filter.Integer.invalid("22");
            assertTrue(true);
        } catch (final InvalidStringException | InvalidIntegerException ex) {
            fail();
        }
    }

    @Test
    public void validNegativeInteger() {
        try {
            Filter.Integer.invalid("-22");
            assertTrue(true);
        } catch (final InvalidStringException | InvalidIntegerException ex) {
            fail();
        }
    }

    @Test
    public void invalidInteger() {
        try {
            Filter.Integer.invalid("três");
            fail();
        } catch (final InvalidStringException | InvalidIntegerException ex) {
            assertEquals("Invalid integer.", ex.getMessage());
        }
    }

    @Test
    public void invalidNullInteger() {
        try {
            Filter.Integer.invalid(null);
            fail();
        } catch (final InvalidStringException | InvalidIntegerException ex) {
            assertEquals("Invalid string.", ex.getMessage());
        }
    }

    @Test
    public void invalidEmptyInteger() {
        try {
            Filter.Integer.invalid("");
            fail();
        } catch (final InvalidStringException | InvalidIntegerException ex) {
            assertEquals("Invalid string.", ex.getMessage());
        }
    }

    @Test
    public void validPositiveDouble() {
        try {
            Filter.Double.invalid("22.2");
            Filter.Double.invalid("22,2");
            assertTrue(true);
        } catch (final InvalidStringException | InvalidDoubleException ex) {
            fail();
        }
    }

    @Test
    public void validNegativeDouble() {
        try {
            Filter.Double.invalid("-22.2");
            Filter.Double.invalid("-22,2");
            assertTrue(true);
        } catch (final InvalidStringException | InvalidDoubleException ex) {
            fail();
        }
    }

    @Test
    public void invalidDouble() {
        try {
            Filter.Double.invalid("três");
            fail();
        } catch (final InvalidStringException | InvalidDoubleException ex) {
            assertEquals("Invalid double.", ex.getMessage());
        }
    }

    @Test
    public void invalidNullDouble() {
        try {
            Filter.Double.invalid(null);
            fail();
        } catch (final InvalidStringException | InvalidDoubleException ex) {
            assertEquals("Invalid string.", ex.getMessage());
        }
    }

    @Test
    public void invalidEmptyDouble() {
        try {
            Filter.Double.invalid("");
            fail();
        } catch (final InvalidStringException | InvalidDoubleException ex) {
            assertEquals("Invalid string.", ex.getMessage());
        }
    }

    @Test
    public void removeChar() {
        assertEquals("BC", Filter.String.remove("ABC", 'A'));
        assertEquals("AC", Filter.String.remove("ABC", 'B'));
        assertEquals("AB", Filter.String.remove("ABC", 'C'));
        assertEquals("ABC", Filter.String.remove("ABC", 'D'));
    }

}
