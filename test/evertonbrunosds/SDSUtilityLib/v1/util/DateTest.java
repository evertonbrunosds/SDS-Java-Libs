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
 *
 * @author evertonbrunosds
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
        final Date date = new Date(1,2,2020);
        assertEquals(1, date.getDay());
        assertEquals(2, date.getMonth());
        assertEquals(2020, date.getYear());
        assertEquals("01/02/2020", date.toString());
    }
    
    @Test
    public void setDay() throws InvalidDateException {
        final Date date = new Date(1,2,2020);
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
        final Date date = new Date(1,2,2020);
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
        final Date date = new Date(1,2,2020);
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
}
