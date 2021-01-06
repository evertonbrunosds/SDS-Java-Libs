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
package evertonbrunosds.SDSUtilityLib.v1.exceptions;

/**
 * Classe responsável por comportar-se como exceção de valor inteiro inválido.
 * @author Everton Bruno Silva dos Santos.
 * @version 1.0
 * @since 1.0
 */
public class InvalidIntegerException extends Exception {
    /**
     * Refere-se ao número de série da exceção de valor inteiro inválido.
     */
    private transient static final long serialVersionUID = -1089770017248175608L;
    /**
     * Refere-se ao valor inválido.
     */
    private final String value;

    /**
     * Construtor responsável pelo instanciamento da exceção de valor inteiro inválido.
     * @param value Refere-se ao dito valor inválido.
     */
    public InvalidIntegerException(final String value) {
        super("Invalid integer.");
        this.value = value;
    }

    /**
     * Método responsável por retornar o valor inválido.
     * @return Retorna o valor inválido.
     */
    public String getValue() {
        return value;
    }

}
