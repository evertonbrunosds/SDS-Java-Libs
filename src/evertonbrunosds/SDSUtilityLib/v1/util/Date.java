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
    /**
     * Refere-se ao número de série da data.
     */
    private static final long serialVersionUID = 9140421242376164174L;
    /**
     * Refere-se ao dia contido na data.
     */
    private int day;
    /**
     * Refere-se ao mês contido na data.
     */
    private int month;
    /**
     * Refere-se ao ano contido na data.
     */
    private int year;

    /**
     * Construtor responsável pelo instanciamento da data.
     */
    public Date() {
        day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        month = Calendar.getInstance().get(Calendar.MONTH) + 1;
        year = Calendar.getInstance().get(Calendar.YEAR);
    }

    /**
     * Construtor responsável pelo instanciamento da data.
     * @param day   Refere-se ao dia contido na data.
     * @param month Refere-se ao mês contido na data.
     * @param year  Refere-se ao ano contido na data.
     * @throws InvalidDateException Exceção lançada no caso da data ser inválida.
     */
    public Date(final int day, final int month, final int year) throws InvalidDateException {
        update(day, month, year);
    }

    /**
     * Método responsável por retornar o dia contido na data.
     * @return Retorna o dia contido na data.
     */
    public int getDay() {
        return day;
    }

    /**
     * Método responsável por alterar o dia contido na data.
     * @param day Refere-se ao dia contido na data.
     * @throws InvalidDateException Exceção lançada no caso da data ser inválida.
     */
    public void setDay(final int day) throws InvalidDateException {
        update(day, month, year);
    }

    /**
     * Método responsável por retornar o mês contido na data.
     * @return Retorna o mês contido na data.
     */
    public int getMonth() {
        return month;
    }

    /**
     * Método responsável por alterar o mês contido na data.
     * @param month Refere-se ao mês contido na data.
     * @throws InvalidDateException Exceção lançada no caso da data ser inválida.
     */
    public void setMonth(final int month) throws InvalidDateException {
        update(day, month, year);
    }

    /**
     * Método responsável por retornar o ano contido na data.
     * @return Retorna o ano contido na data.
     */
    public int getYear() {
        return year;
    }

    /**
     * Método responsável por alterar o ano contido na data.
     * @param year Refere-se ao ano contido na data.
     * @throws InvalidDateException Exceção lançada no caso da data ser inválida.
     */
    public void setYear(final int year) throws InvalidDateException {
        update(day, month, year);
    }

    /**
     * Método responsável por atualizar os dados contidos na data.
     * @param day   Refere-se ao dia contido na data.
     * @param month Refere-se ao mês contido na data.
     * @param year  Refere-se ao ano contido na data.
     * @throws InvalidDateException Exceção lançada no caso da data ser inválida.
     */
    public final void update(int day, int month, int year) throws InvalidDateException {
        day = Converter.Integer.toPositive(day);
        month = Converter.Integer.toPositive(month);
        year = Converter.Integer.toPositive(year);
        Filter.Date.invalid(day, month, year);
        this.day = day;
        this.month = month;
        this.year = year;
    }

    /**
     * Método responsável por duplicar a data.
     * @return Retorna data duplicata.
     */
    @Override
    public Date duplicate() {
        final Date date = new Date();
        date.day = day;
        date.month = month;
        date.year = year;
        return date;
    }

    /**
     * Método responsável por efetuar comparações entre duas datas.
     * @param x Refere-se a primeira data.
     * @param y Refere-se a segunda data.
     * @return resultado da comparação.
     */
    public static int compare(final Date x, final Date y) {
        final int r1 = Integer.compare(x.day, y.day);
        final int r2 = Integer.compare(x.month, y.month);
        final int r3 = Integer.compare(x.year, y.year);
        return r3 != 0 ? r3 : r2 != 0 ? r2 : r1;
    }

    /**
     * Método responsável por efetuar comparação de uma data com outra.
     * @param o Refere-se a data a ser comparada.
     * @return Retorna resultado da comparação.
     */
    @Override
    public int compareTo(final Date o) {
        return compare(this, o);
    }

    /**
     * Método responsável por converter para String de no mínimo dois dígitos,
     * um dado número inteiro.
     * @param i Refere-se ao dado número inteiro.
     * @return Retorna String de no mínimo dois dígitos.
     */
    private static String toString(final int i) {
        return i < 10 ? "0" + i : "" + i;
    }

    /**
     * Método responsável por converter para String os dados da data.
     * @return Retorna em String os dados da data.
     */
    @Override
    public String toString() {
        return toString(day) + "/" + toString(month) + "/" + toString(year);
    }

}
