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

/**
 * Classe responsável por comportar-se como requisição de despesa contábil.
 * @author Everton Bruno Silva dos Santos.
 * @version 1.0
 * @since 1.0
 */
public class AccountingExpenseRequisition extends AccountingFlowRequisition {
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
        super(amount, item);
    }

    /**
     * Método responsável por duplicar a requisição de despesa contábil.
     * @return Retorna requisição de despesa contábil duplicata.
     */
    @Override
    public AccountingExpenseRequisition duplicate() {
        return (AccountingExpenseRequisition) super.duplicate();
    }

    /**
     * Método responsável por retornar a despesa contábil.
     * @return Retorna a despesa contábil.
     */
    @Override
    public AccountingExpense getItem() {
        return (AccountingExpense) super.getItem();
    }

}
