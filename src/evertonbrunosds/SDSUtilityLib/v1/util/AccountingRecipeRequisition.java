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
 * Classe responsável por comportar-se como requisição de receita contábil.
 * @author Everton Bruno Silva dos Santos.
 * @version 1.0
 * @since 1.0
 */
public class AccountingRecipeRequisition extends AbstractRequest<AccountingRecipe> {
    /**
     * Refere-se ao número de série da requisição de receita contábil.
     */
    private static final long serialVersionUID = -3635789802474637479L;

    /**
     * Construtor responsável pelo instanciamento da requisição de receita contábil.
     * @param amount Refere-se a quantidade em que a receita contábil se apresenta.
     * @param item   Refere-se a receita contábil.
     */
    public AccountingRecipeRequisition(final double amount, final AccountingRecipe item) {
        super(Converter.Double.toPositive(amount), item);
    }

    /**
     * Método responsável por alterar a quantidade da requisição de receita contábil.
     * @param amount Refere-se a quantidade da requisição de receita contábil.
     */
    @Override
    public void setAmount(final double amount) {
        super.setAmount(Converter.Double.toPositive(amount));
    }

    /**
     * Método responsável por retornar o valor da requisição de receita contábil.
     * @return Retorna o valor da requisição de receita contábil.
     */
    @Override
    public double getValue() {
        return getItem() == null ? 0 : getAmount() * getItem().getValue();
    }

    /**
     * Método responsável por duplicar a requisição de receita contábil.
     * @return Retorna requisição de receita contábil duplicata.
     */
    @Override
    public AccountingRecipeRequisition duplicate() {
        if (getItem() == null) {
            return new AccountingRecipeRequisition(getAmount(), null);
        } else {
            return new AccountingRecipeRequisition(getAmount(), getItem().duplicate());
        }
    }

    /**
     * Método responsável por efetuar comparações entre duas requisições de receita contábil.
     * @param x Refere-se a primeira receita contábil.
     * @param y Refere-se a segunda receita contábil.
     * @return Retorna o resultado da comparação.
     */
    public static int compare(final AbstractRequest<AccountingRecipe> x, final AbstractRequest<AccountingRecipe> y) {
        if (x != null && y != null) {
            final int result = AccountingRecipe.compare(x.getItem(), y.getItem());
            return result != 0 ? result : Double.compare(x.getAmount(), y.getAmount());
        } else {
            return x != null && y == null ? 1 : x == null && y != null ? -1 : 0;
        }
    }

    /**
     * Método responsável por efetuar comparação de uma requisição de receita contábil com outra.
     * @param ar Refere-se a requisição de receita contábil a ser comparada.
     * @return Retorna resultado da comparação.
     */
    @Override
    public int compareTo(final AbstractRequest<AccountingRecipe> ar) {
        return compare(this, ar);
    }

}
