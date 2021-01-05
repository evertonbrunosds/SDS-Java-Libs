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

import java.io.Serializable;

/**
 * Interface responsável por fornecer as assinaturas de métodos de um comparador.
 * @author Everton Bruno Silva dos Santos.
 * @param <T> Refere-se ao tipo do comparador.
 * @version 1.0
 * @since 1.0
 */
@FunctionalInterface
public interface Comparator<T> extends java.util.Comparator<T>, Serializable {
}
