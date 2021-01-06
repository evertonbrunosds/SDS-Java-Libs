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

import java.text.DecimalFormat;
import evertonbrunosds.SDSUtilityLib.v1.exceptions.InvalidDoubleException;
import evertonbrunosds.SDSUtilityLib.v1.exceptions.InvalidIntegerException;
import evertonbrunosds.SDSUtilityLib.v1.exceptions.InvalidStringException;

/**
 * Interface responsável por fornecer a assinatura de objeto conversor.
 * @author Everton Bruno Silva dos Santos.
 * @version 1.0
 * @since 1.0
 */
public interface Converter {

    /**
     * Classe responsável por comportar-se como um conversor de String.
     * @author Everton Bruno Silva dos Santos.
     * @version 1.0
     * @since 1.0
     */
    public static final class String {

        /**
         * Método responsável pela conversão de um valor em String para inteiro.
         * @param value Refere-se ao dito valor inteiro.
         * @return Retorna o dito valor convertido para inteiro.
         * @throws InvalidStringException  Exceção lançada no caso da String ser inválida.
         * @throws InvalidIntegerException Exceção lançada no caso do valor inteiro ser inválido.
         */
        public static int toInteger(final java.lang.String value)
                throws InvalidStringException, InvalidIntegerException {
            Filter.Integer.invalid(value);
            return java.lang.Integer.parseInt(Filter.String.remove(value, '.'));
        }

        /**
         * Método responsável pela conversão de um valor em String para decimal.
         * @param value Refere-se ao dito valor decimal.
         * @return Retorna o dito valor convertido para inteiro.
         * @throws InvalidStringException Exceção lançada no caso da String ser inválida.
         * @throws InvalidDoubleException Exceção lançada no caso do valor decimal ser inválido.
         */
        public static double toDouble(final java.lang.String value)
                throws InvalidStringException, InvalidDoubleException {
            Filter.Double.invalid(value);
            return java.lang.Double.parseDouble(value.replace(',', '.'));
        }

    }

    /**
     * Classe responsável por comportar-se como um conversor de valor inteiro.
     * @author Everton Bruno Silva dos Santos.
     * @version 1.0
     * @since 1.0
     */
    public static final class Integer {

        /**
         * Método responsável por converter para positivo um possível valor inteiro negativo.
         * @param value Refere-se ao valor inteiro possívelmente negativo.
         * @return Retorna valor inteiro positivo.
         */
        public static int toPositive(final int value) {
            return value < 0 ? value * -1 : value;
        }

        /**
         * Método responsável por converter para negativo um possível valor inteiro positivo.
         * @param value Refere-se ao valor inteiro possívelmente positivo.
         * @return Retorna valor inteiro negativo.
         */
        public static int toNegative(final int value) {
            return value > 0 ? value * -1 : value;
        }

        /**
         * Método responsável pela conversão de um valor inteiro para String.
         * @param value Refere-se ao dito valor inteiro.
         * @return Retorna valor inteiro convertido para String.
         */
        public static java.lang.String toString(final int value) {
            final DecimalFormat decimalFormat = new DecimalFormat("#,###");
            return decimalFormat.format(value);
        }

    }

    /**
     * Classe responsável por comportar-se como um conversor de valor decimal.
     * @author Everton Bruno Silva dos Santos.
     * @version 1.0
     * @since 1.0
     */
    public static final class Double {

        /**
         * Método responsável por converter para positivo um possível valor decimal negativo.
         * @param value Refere-se ao valor decimal possívelmente negativo.
         * @return Retorna valor decimal positivo.
         */
        public static double toPositive(final double value) {
            return value < 0 ? value * -1 : value;
        }

        /**
         * Método responsável por converter para negativo um possível valor decimal positivo.
         * @param value Refere-se ao valor decimal possívelmente positivo.
         * @return Retorna valor decimal negativo.
         */
        public static double toNegative(final double value) {
            return value > 0 ? value * -1 : value;
        }

        /**
         * Método responsável pela conversão de um valor decimal para String.
         * @param value Refere-se ao dito valor decimal.
         * @return Retorna valor decimal convertido para String.
         */
        public static java.lang.String toString(final double value) {
            final DecimalFormat decimalFormat = new DecimalFormat("0.00");
            return decimalFormat.format(value);
        }

    }

}
