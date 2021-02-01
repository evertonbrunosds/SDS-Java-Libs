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

import evertonbrunosds.SDSUtilityLib.v1.api.Converter;
import evertonbrunosds.SDSUtilityLib.v1.api.Duplicable;
import evertonbrunosds.SDSUtilityLib.v1.api.Filter;
import evertonbrunosds.SDSUtilityLib.v1.exceptions.InvalidDateException;
import java.io.Serializable;
import java.util.Calendar;

/**
 * Classe responsável por comportar-se como data.
 * @author Everton Bruno Silva dos Santos.
 * @version 1.0
 * @since 1.0
 */
public class Date implements Duplicable<Date>, Comparable<Date>, Serializable {
    private int day;
    private int month;
    private int year;
    
    public Date() {
        day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        month = Calendar.getInstance().get(Calendar.MONTH) + 1;
        year = Calendar.getInstance().get(Calendar.YEAR);
    }
    
    public Date(int day, int month, int year) throws InvalidDateException {
        update(day, month, year);
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) throws InvalidDateException {
        update(day, month, year);
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) throws InvalidDateException {
        update(day, month, year);
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) throws InvalidDateException {
        update(day, month, year);
    }
    
    private void update(int day, int month, int year) throws InvalidDateException {
        day = Converter.Integer.toPositive(day);
        month = Converter.Integer.toPositive(month);
        year = Converter.Integer.toPositive(year);
        Filter.Date.invalid(day, month, year);
        this.day = day;
        this.month = month;
        this.year = year;
    }

    @Override
    public Date duplicate() {
        final Date date = new Date();
        date.day = day;
        date.month = month;
        date.year = year;
        return date;
    }
    
    public static int compare(final Date x, final Date y) {
        final int r1 = Integer.compare(x.day, y.day);
        final int r2 = Integer.compare(x.month, y.month);
        final int r3 = Integer.compare(x.year, y.year);
        return r3 != 0 ? r3 : r2 != 0 ? r2 : r1;
    }

    @Override
    public int compareTo(final Date o) {
        return compare(this, o);
    }
    
}
