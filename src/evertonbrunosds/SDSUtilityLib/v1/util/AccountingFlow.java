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
 * Classe responsável por comportar-se como fluxo contábil.
 * @author Everton Bruno Silva dos Santos.
 * @version 1.0
 * @since 1.0
 */
public class AccountingFlow implements Duplicable<AccountingFlow>, Comparable<AccountingFlow>, Serializable {
    /**
     * Refere-se ao número de série do fluxo contábil.
     */
    private transient static final long serialVersionUID = -2225919978686491997L;
    /**
     * Refere-se ao valor do fluxo contábil.
     */
    private double value;

    /**
     * Construtor responsável pelo instanciamento do fluxo contábil.
     * @param value Refere-se ao valor do fluxo contábil.
     */
    public AccountingFlow(final double value) {
        this.value = value;
    }

    /**
     * Método responsável por retornar o valor do fluxo contábil.
     * @return Retorna o valor do fluxo contábil.
     */
    public double getValue() {
        return value;
    }

    /**
     * Método responsável por alterar o valor do fluxo contábil.
     * @param value Refere-se ao novo valor do fluxo contábil.
     */
    public void setValue(final double value) {
        this.value = value;
    }

    /**
     * Método responsável por duplicar o fluxo contábil.
     * @return Retorna fluxo contábil duplicata.
     */
    @Override
    public AccountingFlow duplicate() {
        return new AccountingFlow(value);
    }

    /**
     * Método responsável por efetuar comparação de um fluxo contábil com outro.
     * @param af Refere-se ao fluxo contábil a ser comparado.
     * @return Retorna resultado da comparação.
     */
    @Override
    public int compareTo(final AccountingFlow af) {
        return Double.compare(this.value, af.value);
    }

    /**
     * Método responsável por converter para String o valor do fluxo contábil.
     * @return Retorna em String o valor do fluxo contábil.
     */
    @Override
    public String toString() {
        return Converter.Double.toString(value);
    }

}
