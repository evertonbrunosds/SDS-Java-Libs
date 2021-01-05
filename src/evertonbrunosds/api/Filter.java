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
                java.lang.Integer.parseInt(value);
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
                java.lang.Double.parseDouble(value.replace(',', '.'));
            } catch (final NumberFormatException ex) {
                throw new InvalidDoubleException(value);
            }
        }

    }

}
