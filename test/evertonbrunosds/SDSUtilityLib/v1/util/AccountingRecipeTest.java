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
 * Classe responsável por efetuar testes na receita contábil.
 * @author Everton Bruno Silva dos Santos.
 * @version 1.0
 * @since 1.0
 */
public class AccountingRecipeTest {
    private AccountingFlow accountingRecipe;

    public AccountingRecipeTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        accountingRecipe = null;
    }

    @After
    public void tearDown() {
    }

    @Test
    public void criandoReceitaContabilComValorNegativo() {
        accountingRecipe = new AccountingRecipe(-2.5);
        assertEquals(2.5, accountingRecipe.getValue(), 0.0);
    }

    @Test
    public void criandoReceitaContabilComValorPositivo() {
        accountingRecipe = new AccountingRecipe(2.5);
        assertEquals(2.5, accountingRecipe.getValue(), 0.0);
    }

    @Test
    public void alterandoReceitaContabilComValorNegativo() {
        accountingRecipe = new AccountingRecipe(0);
        accountingRecipe.setValue(-5.6);
        assertEquals(5.6, accountingRecipe.getValue(), 0.0);
    }

    @Test
    public void alterandoReceitaContabilComValorPositivo() {
        accountingRecipe = new AccountingRecipe(0);
        accountingRecipe.setValue(5.6);
        assertEquals(5.6, accountingRecipe.getValue(), 0.0);
    }

    @Test
    public void verificaIdependênciaDeReceitaContabilDuplicado() {
        accountingRecipe = new AccountingRecipe(0);
        final AccountingFlow accountingRecipeDuplicated = accountingRecipe.duplicate();
        accountingRecipeDuplicated.setValue(200);
        assertEquals(200, accountingRecipeDuplicated.getValue(), 0.0);
        assertEquals(0, accountingRecipe.getValue(), 0.0);
    }

    @Test
    public void comparaReceitaContabil() {
        final AccountingRecipe ar1 = new AccountingRecipe(1);
        final AccountingRecipe ar2 = new AccountingRecipe(2);
        assertEquals(-1, ar1.compareTo(ar2));
        ar2.setValue(1);
        assertEquals(0, ar1.compareTo(ar2));
    }

    @Test
    public void paraStringReceitaContabilPositivo() {
        accountingRecipe = new AccountingRecipe(1357.93);
        assertEquals("1.357,93", accountingRecipe.toString());
    }

    @Test
    public void paraStringReceitaContabilNegativo() {
        accountingRecipe = new AccountingRecipe(-1357.93);
        assertEquals("1.357,93", accountingRecipe.toString());
    }

}
