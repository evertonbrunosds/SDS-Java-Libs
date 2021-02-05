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
package evertonbrunosds.SDSUtilityLib.v1.util;

import evertonbrunosds.SDSUtilityLib.v1.exceptions.InvalidDateException;
import java.util.Calendar;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Classe responsável por efetuar testes na data.
 * @author Everton Bruno Silva dos Santos.
 * @version 1.0
 * @since 1.0
 */
public class DateTest {

    public DateTest() {
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
    public void makeCurrentDate() {
        final Date date = new Date();
        assertEquals(Calendar.getInstance().get(Calendar.DAY_OF_MONTH), date.getDay());
        assertEquals(Calendar.getInstance().get(Calendar.MONTH) + 1, date.getMonth());
        assertEquals(Calendar.getInstance().get(Calendar.YEAR), date.getYear());
    }

    @Test
    public void makeDate() throws InvalidDateException {
        final Date date = new Date(1, 2, 2020);
        assertEquals(1, date.getDay());
        assertEquals(2, date.getMonth());
        assertEquals(2020, date.getYear());
        assertEquals("01/02/2020", date.toString());
    }

    @Test
    public void setDay() throws InvalidDateException {
        final Date date = new Date(1, 2, 2020);
        assertEquals(1, date.getDay());
        assertEquals(2, date.getMonth());
        assertEquals(2020, date.getYear());
        assertEquals("01/02/2020", date.toString());
        date.setDay(5);
        assertEquals(5, date.getDay());
        assertEquals(2, date.getMonth());
        assertEquals(2020, date.getYear());
        assertEquals("05/02/2020", date.toString());
    }

    @Test
    public void setMonth() throws InvalidDateException {
        final Date date = new Date(1, 2, 2020);
        assertEquals(1, date.getDay());
        assertEquals(2, date.getMonth());
        assertEquals(2020, date.getYear());
        assertEquals("01/02/2020", date.toString());
        date.setMonth(10);
        assertEquals(1, date.getDay());
        assertEquals(10, date.getMonth());
        assertEquals(2020, date.getYear());
        assertEquals("01/10/2020", date.toString());
    }

    @Test
    public void setYear() throws InvalidDateException {
        final Date date = new Date(1, 2, 2020);
        assertEquals(1, date.getDay());
        assertEquals(2, date.getMonth());
        assertEquals(2020, date.getYear());
        assertEquals("01/02/2020", date.toString());
        date.setYear(1997);
        assertEquals(1, date.getDay());
        assertEquals(2, date.getMonth());
        assertEquals(1997, date.getYear());
        assertEquals("01/02/1997", date.toString());
    }

    @Test
    public void set() throws InvalidDateException {
        final Date date = new Date(1, 2, 2020);
        assertEquals(1, date.getDay());
        assertEquals(2, date.getMonth());
        assertEquals(2020, date.getYear());
        assertEquals("01/02/2020", date.toString());
        date.set(18, 12, 2010);
        assertEquals(18, date.getDay());
        assertEquals(12, date.getMonth());
        assertEquals(2010, date.getYear());
        assertEquals("18/12/2010", date.toString());
    }

    @Test
    public void makeInvalidDate() {
        try {
            final Date date = new Date(30, 2, 2020);
            fail();
        } catch (final InvalidDateException ex) {
            assertEquals("Invalid date.", ex.getMessage());
            assertEquals("30/02/2020", ex.getDate());
        }
    }

    @Test
    public void setInvalidDay() throws InvalidDateException {
        final Date date = new Date(1, 2, 2020);
        assertEquals(1, date.getDay());
        assertEquals(2, date.getMonth());
        assertEquals(2020, date.getYear());
        assertEquals("01/02/2020", date.toString());
        try {
            date.setDay(32);
            fail();
        } catch (final InvalidDateException ex) {
            assertEquals("Invalid date.", ex.getMessage());
            assertEquals("32/02/2020", ex.getDate());
        }
    }

    @Test
    public void setInvalidMonth() throws InvalidDateException {
        final Date date = new Date(1, 2, 2020);
        assertEquals(1, date.getDay());
        assertEquals(2, date.getMonth());
        assertEquals(2020, date.getYear());
        assertEquals("01/02/2020", date.toString());
        try {
            date.setMonth(13);
            fail();
        } catch (final InvalidDateException ex) {
            assertEquals("Invalid date.", ex.getMessage());
            assertEquals("01/13/2020", ex.getDate());
        }
    }

    @Test
    public void setInvalid() throws InvalidDateException {
        final Date date = new Date(1, 2, 2020);
        assertEquals(1, date.getDay());
        assertEquals(2, date.getMonth());
        assertEquals(2020, date.getYear());
        assertEquals("01/02/2020", date.toString());
        try {
            date.set(32, 16, 2021);
            fail();
        } catch (final InvalidDateException ex) {
            assertEquals("Invalid date.", ex.getMessage());
            assertEquals("32/16/2021", ex.getDate());
        }
        try {
            date.set(07, 14, 2019);
            fail();
        } catch (final InvalidDateException ex) {
            assertEquals("Invalid date.", ex.getMessage());
            assertEquals("07/14/2019", ex.getDate());
        }
        assertEquals(1, date.getDay());
        assertEquals(2, date.getMonth());
        assertEquals(2020, date.getYear());
        assertEquals("01/02/2020", date.toString());
    }

    @Test
    public void duplicate() throws InvalidDateException {
        final Date date = new Date(1, 2, 2020);
        final Date date2 = date.duplicate();
        date2.set(5, 10, 2010);
        assertEquals(1, date.getDay());
        assertEquals(2, date.getMonth());
        assertEquals(2020, date.getYear());
        assertEquals("01/02/2020", date.toString());
        assertEquals(5, date2.getDay());
        assertEquals(10, date2.getMonth());
        assertEquals(2010, date2.getYear());
        assertEquals("05/10/2010", date2.toString());
    }

    @Test
    public void compareNotNullWithNotNull() throws InvalidDateException {
        final Date d1 = new Date(1, 2, 2020);
        final Date d2 = d1.duplicate();
        assertEquals(0, Date.compare(d1, d2));
        d1.setDay(2);
        assertEquals(1, Date.compare(d1, d2));
        d2.setDay(3);
        assertEquals(-1, Date.compare(d1, d2));
    }

    @Test
    public void compareNullWithNull() throws InvalidDateException {
        assertEquals(0, Date.compare(null, null));
    }

    @Test
    public void compareNotNullWithNull() throws InvalidDateException {
        final Date d1 = new Date(1, 2, 2020);
        assertEquals(1, Date.compare(d1, null));
    }

    @Test
    public void compareNullWithNotNull() throws InvalidDateException {
        final Date d1 = new Date(1, 2, 2020);
        assertEquals(-1, Date.compare(null, d1));
    }

    @Test
    public void compareTo() throws InvalidDateException {
        final Date d1 = new Date(1, 2, 2020);
        final Date d2 = d1.duplicate();
        assertEquals(0, d1.compareTo(d2));
        d1.setDay(2);
        assertEquals(1, d1.compareTo(d2));
        d2.setDay(3);
        assertEquals(-1, d1.compareTo(d2));
    }

    @Test
    public void compareToNull() throws InvalidDateException {
        final Date d1 = new Date(1, 2, 2020);
        assertEquals(1, d1.compareTo(null));
    }

}
