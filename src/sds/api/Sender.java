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
package sds.api;

import java.io.Serializable;

/**
 * Interface responsável por fornecer a assinatura de método de um remetente.
 * @author Everton Bruno Silva dos Santos.
 * @param <T> Refere-se ao tipo de dados a ser enviado.
 * @version 1.0
 */
@FunctionalInterface
public interface Sender<T> extends Serializable {

    /**
     * Método responsável por enviar uma remereça de dados.
     * @return Retorna a dita remereça de dados.
     */
    T send();

}
