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

/**
 * Classe responsável por comportar-se como requisição de fluxo contábil.
 * @author Everton Bruno Silva dos Santos.
 * @version 1.0
 * @since 1.0
 */
public class AccountingFlowRequisition extends AbstractRequest<AccountingFlow> {
    /**
     * Refere-se ao número de série da requisição de fluxo contábil.
     */
    private static final long serialVersionUID = 5996450644652346954L;

    /**
     * Construtor responsável pelo instanciamento da requisição de fluxo contábil.
     * @param amount Refere-se a quantidade em que o fluxo contábil se apresenta.
     * @param item   Refere-se ao fluxo contábil.
     */
    public AccountingFlowRequisition(final double amount, final AccountingFlow item) {
        super(Converter.Double.toPositive(amount), item);
    }

    /**
     * Método responsável por alterar a quantidade da requisição de fluxo contábil.
     * @param amount Refere-se a quantidade da requisição de fluxo contábil.
     */
    @Override
    public void setAmount(final double amount) {
        super.setAmount(Converter.Double.toPositive(amount));
    }

    /**
     * Método responsável por retornar o valor da requisição de fluxo contábil.
     * @return Retorna o valor da requisição de fluxo contábil.
     */
    @Override
    public double getValue() {
        return getItem() == null ? 0 : getAmount() * getItem().getValue();
    }

    /**
     * Método responsável por duplicar a requisição de fluxo contábil.
     * @return Retorna requisição de fluxo contábil duplicata.
     */
    @Override
    public AccountingFlowRequisition duplicate() {
        if (getItem() == null) {
            return new AccountingFlowRequisition(getAmount(), null);
        } else {
            return new AccountingFlowRequisition(getAmount(), getItem().duplicate());
        }
    }

    /**
     * Método responsável por efetuar comparações entre duas requisições de fluxo contábil.
     * @param x Refere-se ao primeiro fluxo contábil.
     * @param y Refere-se ao segundo fluxo contábil.
     * @return Retorna o resultado da comparação.
     */
    public static int compare(final AbstractRequest<AccountingFlow> x, final AbstractRequest<AccountingFlow> y) {
        if (x != null && y != null) {
            return AccountingFlow.compare(x.getItem(), y.getItem());
        } else {
            return x != null && y == null ? 1 : x == null && y != null ? -1 : 0;
        }
    }

    /**
     * Método responsável por efetuar comparação de uma requisição de fluxo contábil com outra.
     * @param ar Refere-se a requisição de fluxo contábil a ser comparada.
     * @return Retorna resultado da comparação.
     */
    @Override
    public int compareTo(final AbstractRequest<AccountingFlow> ar) {
        return compare(this, ar);
    }

}
