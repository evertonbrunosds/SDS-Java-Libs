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
package evertonbrunosds.api;

import evertonbrunosds.exceptions.InvalidDoubleException;
import evertonbrunosds.exceptions.InvalidIntegerException;
import evertonbrunosds.exceptions.InvalidStringException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Classe responsável por efetuar testes no conversor.
 * @author Everton Bruno Silva dos Santos.
 * @version 1.0
 * @since 1.0
 */
public class ConverterTest {

    public ConverterTest() {
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
    public void stringToPositiveInteger() throws InvalidStringException, InvalidIntegerException {
        assertEquals(22, Converter.String.toInteger("22"));
    }

    @Test
    public void stringToNegativeInteger() throws InvalidStringException, InvalidIntegerException {
        assertEquals(-22, Converter.String.toInteger("-22"));
    }

    @Test
    public void stringToPositiveDouble() throws InvalidStringException, InvalidDoubleException {
        assertEquals(22.5, Converter.String.toDouble("22.5"), 0.00);
        assertEquals(22.5, Converter.String.toDouble("22,5"), 0.00);
    }

    @Test
    public void stringToNegativeDouble() throws InvalidStringException, InvalidDoubleException {
        assertEquals(-22.5, Converter.String.toDouble("-22.5"), 0.00);
        assertEquals(-22.5, Converter.String.toDouble("-22,5"), 0.00);
    }

    @Test
    public void integerValueToPositive() {
        assertEquals(22, Converter.Integer.toPositive(-22));
    }

    @Test
    public void integerValueToNegative() {
        assertEquals(-22, Converter.Integer.toNegative(22));
    }

    @Test
    public void integerValueToString() {
        assertEquals("22", Converter.Integer.toString(22));
        assertEquals("-22", Converter.Integer.toString(-22));
    }

    @Test
    public void doubleValueToPositive() {
        assertEquals(22.5, Converter.Double.toPositive(-22.5), 0.00);
    }

    @Test
    public void doubleValueToNegative() {
        assertEquals(-22.5, Converter.Double.toNegative(22.5), 0.00);
    }

    @Test
    public void doubleValueToString() {
        assertEquals("22,50", Converter.Double.toString(22.50));
        assertEquals("-22,50", Converter.Double.toString(-22.50));
    }
}
