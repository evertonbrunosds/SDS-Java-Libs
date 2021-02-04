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
package evertonbrunosds.SDSUtilityLib.v1.util;

import evertonbrunosds.SDSUtilityLib.v1.api.Converter;
import evertonbrunosds.SDSUtilityLib.v1.api.Duplicable;
import java.io.Serializable;

/**
 * Classe responsável por comportar-se como requisição abstrata.
 * @author Everton Bruno Silva dos Santos.
 * @version 1.0
 * @param <I> Refere-se ao tipo do item da requisição.
 * @since 1.0
 */
public abstract class AbstractRequest<I> implements Duplicable<AccountingFlow>, Comparable<AccountingFlow>, Serializable {
    /**
     * Refere-se ao número de série da requisição abstrata.
     */
    private static final long serialVersionUID = -5335164362195553852L;
    /**
     * Refere-se a quantidade da requisição.
     */
    private double amount;
    /**
     * Refere-se ao item requisitado.
     */
    private I item;

    /**
     * Construtor responsável por possibilitar o instanciamento das subclasses da requisição abstrata.
     * @param amount Refere-se a quantidade da requisição.
     * @param item   Refere-se ao item requisitado.
     */
    public AbstractRequest(final double amount, final I item) {
        this.amount = amount;
        this.item = item;
    }

    /**
     * Método responsável por retornar o item requisitado.
     * @return Retorna o item requisitado.
     */
    public I getItem() {
        return item;
    }

    /**
     * Método responsável por alterar o item requisitado.
     * @param item Refere-se ao item requisitado.
     */
    public void setItem(final I item) {
        this.item = item;
    }

    /**
     * Método responsável por retornar a quantidade da requisição.
     * @return Retorna a quantidade da requisição.
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Método responsável por alterar a quantidade da requisição.
     * @param amount Refere-se a quantidade da requisição.
     */
    public void setAmount(final double amount) {
        this.amount = amount;
    }

    /**
     * Método responsável por retornar o valor da requisição.
     * @return Retorna o valor da requisição.
     */
    public abstract double getValue();

    /**
     * Método responsável por converter para String o valor da requisição.
     * @return Retorna em String o valor da requisição.
     */
    @Override
    public String toString() {
        return Converter.Double.toString(getValue());
    }

}
