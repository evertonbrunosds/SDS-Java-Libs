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
 * Classe responsável por comportar-se como requisição de despesa contábil.
 * @author Everton Bruno Silva dos Santos.
 * @version 1.0
 * @since 1.0
 */
public class AccountingExpenseRequisition extends AbstractRequest<AccountingExpense> {
    /**
     * Refere-se ao número de série da requisição de despesa contábil.
     */
    private static final long serialVersionUID = 6775677341908526653L;

    /**
     * Construtor responsável pelo instanciamento da requisição de despesa contábil.
     * @param amount Refere-se a quantidade em que a despesa contábil se apresenta.
     * @param item   Refere-se a despesa contábil.
     */
    public AccountingExpenseRequisition(final double amount, final AccountingExpense item) {
        super(Converter.Double.toPositive(amount), item);
    }

    /**
     * Método responsável por alterar a quantidade da requisição de despesa contábil.
     * @param amount Refere-se a quantidade da requisição de despesa contábil.
     */
    @Override
    public void setAmount(final double amount) {
        super.setAmount(Converter.Double.toPositive(amount));
    }

    /**
     * Método responsável por retornar o valor da requisição de despesa contábil.
     * @return Retorna o valor da requisição de despesa contábil.
     */
    @Override
    public double getValue() {
        return getItem() == null ? 0 : getAmount() * getItem().getValue();
    }

    /**
     * Método responsável por duplicar a requisição de despesa contábil.
     * @return Retorna requisição de despesa contábil duplicata.
     */
    @Override
    public AccountingExpenseRequisition duplicate() {
        if (getItem() == null) {
            return new AccountingExpenseRequisition(getAmount(), null);
        } else {
            return new AccountingExpenseRequisition(getAmount(), getItem().duplicate());
        }
    }

    /**
     * Método responsável por efetuar comparações entre duas requisições de despesa contábil.
     * @param x Refere-se a primeira despesa contábil.
     * @param y Refere-se a segunda despesa contábil.
     * @return Retorna o resultado da comparação.
     */
    public static int compare(final AbstractRequest<AccountingExpense> x, final AbstractRequest<AccountingExpense> y) {
        if (x != null && y != null) {
            final int result = AccountingExpense.compare(x.getItem(), y.getItem());
            return result != 0 ? result : Double.compare(x.getAmount(), y.getAmount());
        } else {
            return x != null && y == null ? 1 : x == null && y != null ? -1 : 0;
        }
    }

    /**
     * Método responsável por efetuar comparação de uma requisição de despesa contábil com outra.
     * @param ar Refere-se a requisição de despesa contábil a ser comparada.
     * @return Retorna resultado da comparação.
     */
    @Override
    public int compareTo(final AbstractRequest<AccountingExpense> ar) {
        return compare(this, ar);
    }

}
