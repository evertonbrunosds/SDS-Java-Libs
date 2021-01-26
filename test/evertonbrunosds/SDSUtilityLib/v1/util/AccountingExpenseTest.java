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

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Classe responsável por efetuar testes na despesa contábil.
 * @author Everton Bruno Silva dos Santos.
 * @version 1.0
 * @since 1.0
 */
public class AccountingExpenseTest {
    private AccountingFlow accountingExpense;
    
    public AccountingExpenseTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        accountingExpense = null;
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void criandoDespesaContabilComValorNegativo() {
        accountingExpense = new AccountingExpense(-2.5);
        assertEquals(-2.5, accountingExpense.getValue(), 0.0);
    }

    @Test
    public void criandoDespesaContabilComValorPositivo() {
        accountingExpense = new AccountingExpense(2.5);
        assertEquals(-2.5, accountingExpense.getValue(), 0.0);
    }

    @Test
    public void alterandoDespesaContabilComValorNegativo() {
        accountingExpense = new AccountingExpense(0);
        accountingExpense.setValue(-5.6);
        assertEquals(-5.6, accountingExpense.getValue(), 0.0);
    }

    @Test
    public void alterandoDespesaContabilComValorPositivo() {
        accountingExpense = new AccountingExpense(0);
        accountingExpense.setValue(5.6);
        assertEquals(-5.6, accountingExpense.getValue(), 0.0);
    }

    @Test
    public void verificaIdependênciaDeDespesaContabilDuplicado() {
        accountingExpense = new AccountingExpense(0);
        final AccountingFlow accountingExpenseDuplicated = accountingExpense.duplicate();
        accountingExpenseDuplicated.setValue(200);
        assertEquals(-200, accountingExpenseDuplicated.getValue(), 0.0);
        assertEquals(0, accountingExpense.getValue(), 0.0);
    }

    @Test
    public void comparaDespesaContabil() {
        final AccountingExpense af1 = new AccountingExpense(1);
        final AccountingExpense af2 = new AccountingExpense(2);
        assertEquals(1, af1.compareTo(af2));
        af2.setValue(1);
        assertEquals(0, af1.compareTo(af2));
    }

    @Test
    public void paraStringFluxoContabilPositivo() {
        accountingExpense = new AccountingExpense(1357.93);
        assertEquals("-1.357,93", accountingExpense.toString());
    }

    @Test
    public void paraStringFluxoContabilNegativo() {
        accountingExpense = new AccountingExpense(-1357.93);
        assertEquals("-1.357,93", accountingExpense.toString());
    }

}
