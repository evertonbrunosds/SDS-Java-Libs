/*
 * This file is part of the SDSThree Open Source Project.
 * SDSThree is licensed under the GNU GPLv3.
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
package sds.three;

/**
 * Classe responsável por comportar-se como exceção de entrada não encontrada.
 * @author Everton Bruno Silva dos Santos.
 * @version 1.4
 */
public class EntryNotFoundException extends RuntimeException {
    /**
     * Refere-se ao número de série da exceção de entrada não encontrada.
     */
    private static final long serialVersionUID = -2020384390486923525L;

    /**
     * Construtor responsável pelo instanciamento da exceção de entrada não encontrada.
     * @param msg Refere-se a mensagem da exceção de entrada não encontrada.
     */
    public EntryNotFoundException(final String msg) {
        super(msg);
    }

}