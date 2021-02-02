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

import evertonbrunosds.SDSUtilityLib.v1.exceptions.InvalidDateException;
import evertonbrunosds.SDSUtilityLib.v1.exceptions.InvalidDoubleException;
import evertonbrunosds.SDSUtilityLib.v1.exceptions.InvalidIntegerException;
import evertonbrunosds.SDSUtilityLib.v1.exceptions.InvalidStringException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Interface responsável por fornecer a assinatura de objeto filtrante.
 * @author Everton Bruno Silva dos Santos.
 * @version 1.0
 * @since 1.0
 */
public interface Filter {

    /**
     * Classe responsável por comportar-se como um filtro de String.
     * @author Everton Bruno Silva dos Santos.
     * @version 1.0
     * @since 1.0
     */
    public static final class String {

        /**
         * Método responsável por filtrar uma String possívelmente inválida.
         * @param string Refere-se a dita String possívelmente inválida.
         * @throws InvalidStringException Exceção lançada no caso da String ser inválida.
         */
        public static void invalid(final java.lang.String string) throws InvalidStringException {
            if (string == null) {
                throw new InvalidStringException();
            } else if (string.equals("")) {
                throw new InvalidStringException();
            }
        }

        /**
         * Método responsável por remover dado caractere possívelmente contido em determinada String.
         * @param oldString Refere-se a String antes da remoção de caractere possívelmente contido.
         * @param character Refere-se ao caractere possívelmente contido.
         * @return Retorna nova String com caractere possívelmente contido ausente.
         */
        public static java.lang.String remove(final java.lang.String oldString, final char character) {
            if (oldString.contains(java.lang.String.valueOf(character))) {
                java.lang.String newString = new java.lang.String();
                for (int i = 0; i < oldString.length(); i++) {
                    if (oldString.charAt(i) != character) {
                        newString += oldString.charAt(i);
                    }
                }
                return newString;
            }
            return oldString;
        }

    }

    /**
     * Classe responsável por comportar-se como um filtro de valor inteiro.
     * @author Everton Bruno Silva dos Santos.
     * @version 1.0
     * @since 1.0
     */
    public static final class Integer {

        /**
         * Método responsável por filtrar um valor inteiro possívelmente inválido.
         * @param value Refere-se ao dito valor inteiro possívelmente inválido.
         * @throws InvalidStringException  Exceção lançada no caso da String ser inválida.
         * @throws InvalidIntegerException Exceção lançada no caso do valor inteiro ser inválido.
         */
        public static void invalid(final java.lang.String value)
                throws InvalidStringException, InvalidIntegerException {
            String.invalid(value);
            try {
                java.lang.Integer.parseInt(String.remove(value, '.'));
            } catch (final NumberFormatException ex) {
                throw new InvalidIntegerException(value);
            }
        }

    }

    /**
     * Classe responsável por comportar-se como um filtro de valor decimal.
     * @author Everton Bruno Silva dos Santos.
     * @version 1.0
     * @since 1.0
     */
    public static final class Double {

        /**
         * Método responsável por filtrar um valor decimal possívelmente inválido.
         * @param value Refere-se ao dito valor decimal possívelmente inválido.
         * @throws InvalidStringException Exceção lançada no caso da String ser inválida.
         * @throws InvalidDoubleException Exceção lançada no caso do valor decimal ser inválido.
         */
        public static void invalid(final java.lang.String value) throws InvalidStringException, InvalidDoubleException {
            String.invalid(value);
            try {
                java.lang.Double.parseDouble(String.remove(value, '.').replace(',', '.'));
            } catch (final NumberFormatException ex) {
                throw new InvalidDoubleException(value);
            }
        }

    }

    /**
     * Classe responsável por comportar-se como um filtro de data.
     * @author Everton Bruno Silva dos Santos.
     * @version 1.0
     * @since 1.0
     */
    public static final class Date {
        
        /**
         * Método responsável por converter para String de no mínimo dois dígitos,
         * um dado número inteiro.
         * @param i Refere-se ao dado número inteiro.
         * @return Retorna String de no mínimo dois dígitos.
         */
        private static java.lang.String toString(final int i) {
            return i < 10 ? "0" + i : "" + i;
        }

        /**
         * Método responsável por filtrar uma data possívelmente inválida.
         * @param date Refere-se a dita data possívelmente inválida.
         * @throws InvalidStringException Exceção lançada no caso da String ser inválida.
         * @throws InvalidDateException   Exceção lançada no caso da data ser inválida.
         */
        public static void invalid(final java.lang.String date) throws InvalidStringException, InvalidDateException {
            String.invalid(date);
            verify(date);
        }

        /**
         * Método responsável por filtrar uma data possívelmente inválida.
         * @param day   Refere-se ao dia contido na data.
         * @param month Refere-se ao mês contido na data.
         * @param year  Refere-se ao ano contido na data.
         * @throws InvalidDateException Exceção lançada no caso da data ser inválida.
         */
        public static void invalid(final int day, final int month, final int year) throws InvalidDateException {
            verify(toString(day) + "/" + toString(month) + "/" + toString(year));
        }

        /**
         * Método responsável por filtrar uma data possívelmente inválida.
         * @param date Refere-se a dita data possívelmente inválida.
         * @throws InvalidDateException Exceção lançada no caso da data ser inválida.
         */
        private static void verify(final java.lang.String date) throws InvalidDateException {
            final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            dateFormat.setLenient(false);
            try {
                dateFormat.parse(date);
            } catch (final ParseException ex) {
                throw new InvalidDateException(date);
            }
        }

    }
}
