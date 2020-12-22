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

import java.io.Serializable;

/**
 * Interface responsável por fornecer a assinatura do métodos de um comparador.
 * @author Everton Bruno Silva dos Santos.
 * @param <V> Refere-se ao tipo de valor comparável.
 * @version 1.1
 */
@FunctionalInterface
public interface Comparator<V> extends Serializable {

    /**
     * Método responsável por efetuar a comparação entre dos objetos de mesmo tipo.
     * @param x Refere-se ao primeiro objeto.
     * @param y Refere-se ao segundo objeto.
     * @return Retorna o resultado da comparação.
     */
    int compare(final V x, final V y);

}
