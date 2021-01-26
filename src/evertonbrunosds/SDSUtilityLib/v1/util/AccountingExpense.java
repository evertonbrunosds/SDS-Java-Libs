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
 * Classe responsável por comportar-se como despesa contábil.
 * @author Everton Bruno Silva dos Santos.
 * @version 1.0
 * @since 1.0
 */
public class AccountingExpense extends AccountingFlow {
    /**
     * Refere-se ao número de série da despesa contábil.
     */
    private transient static final long serialVersionUID = -2225946958686492020L;

    /**
     * Construtor responsável pelo instanciamento da despesa contábil.
     * @param value Refere-se ao valor da despesa contábil.
     */
    public AccountingExpense(final double value) {
        super(Converter.Double.toNegative(value));
    }

    /**
     * Método responsável por alterar o valor da despesa contábil.
     * @param value Refere-se ao novo valor da despesa contábil.
     */
    @Override
    public void setValue(final double value) {
        super.setValue(Converter.Double.toNegative(value));
    }

    /**
     * Método responsável por duplicar a despesa contábil.
     * @return Retorna despesa contábil duplicata.
     */
    @Override
    public AccountingExpense duplicate() {
        return new AccountingExpense(getValue());
    }

}
