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

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Interface responsável por fornecer as assinaturas dos métodos de um arquivo em fluxo.
 * @author Everton Bruno Silva dos Santos.
 * @param <T> Refere-se ao tipo de arquivo em fluxo.
 * @version 1.0
 * @since 1.0
 */
public interface FileStream<T> extends Serializable {

    /**
     * Método responsável por alterar os dados do arquivo em fluxo.
     * @param newData Refere-se aos novos dados da estrutura.
     */
    void set(final T newData);

    /**
     * Método responsável por carregar dados de arquivo.
     * @param fileName Refere-se ao nome do arquivo.
     * @throws IOException            Exceção lançada no caso de haverem problemas de entrada.
     * @throws ClassNotFoundException Exceção lançada no caso de não haver classe no arquivo.
     * @throws ClassCastException     Exceção lançada no caso do tipo ser incompatível.
     */
    @SuppressWarnings("unchecked")
    default void loadFromFile(final String fileName) throws IOException, ClassNotFoundException, ClassCastException {
        try (final ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(fileName))) {
            this.set((T) objectInputStream.readObject());
            objectInputStream.close();
        }
    }

    /**
     * Método responsável por gravar dados em arquivo.
     * @param fileName Refere-se ao nome do arquivo.
     * @throws IOException Exceção lançada no caso de haverem problemas de saída.
     */
    default void saveToFile(final String fileName) throws IOException {
        try (final ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            objectOutputStream.writeObject(this);
            objectOutputStream.close();
        }
    }

}
