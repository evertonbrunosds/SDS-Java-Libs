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
 * Classe responsável por comportar-se como exceção de data inválida.
 * @author Everton Bruno Silva dos Santos.
 * @version 1.0
 * @since 1.0
 */
public class InvalidDateException extends RuntimeException {
    /**
     * Refere-se ao número de série da exceção de data inválida.
     */
    private static final long serialVersionUID = -1001663209896659033L;
    /**
     * Refere-se a data caracterizada como inválida.
     */
    private final String date;

    /**
     * Construtor responsável pelo instanciamento da exceção de data inválida.
     * @param date Refere-se a data caracterizada como inválida.
     */
    public InvalidDateException(final String date) {
        this.date = date;
    }

    /**
     * Método responsável por retornar a data caracterizada como inválida.
     * @return Retorna a data caracterizada como inválida.
     */
    public String getDate() {
        return date;
    }

}
