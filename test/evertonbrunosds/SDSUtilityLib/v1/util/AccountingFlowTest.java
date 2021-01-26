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
 * Classe responsável por efetuar testes no fluxo contábil.
 * @author Everton Bruno Silva dos Santos.
 * @version 1.0
 * @since 1.0
 */
public class AccountingFlowTest {
    private AccountingFlow accountingFlow;

    public AccountingFlowTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        accountingFlow = null;
    }

    @After
    public void tearDown() {
    }

    @Test
    public void criandoFluxoContabilComValorNegativo() {
        accountingFlow = new AccountingFlow(-2.5);
        assertEquals(-2.5, accountingFlow.getValue(), 0.0);
    }

    @Test
    public void criandoFluxoContabilComValorPositivo() {
        accountingFlow = new AccountingFlow(2.5);
        assertEquals(2.5, accountingFlow.getValue(), 0.0);
    }

    @Test
    public void alterandoFluxoContabilComValorNegativo() {
        accountingFlow = new AccountingFlow(0);
        accountingFlow.setValue(-5.6);
        assertEquals(-5.6, accountingFlow.getValue(), 0.0);
    }

    @Test
    public void alterandoFluxoContabilComValorPositivo() {
        accountingFlow = new AccountingFlow(0);
        accountingFlow.setValue(5.6);
        assertEquals(5.6, accountingFlow.getValue(), 0.0);
    }

    @Test
    public void verificaIdependênciaDeFluxoContabilDuplicado() {
        accountingFlow = new AccountingFlow(0);
        final AccountingFlow accountingFlowDuplicated = accountingFlow.duplicate();
        accountingFlowDuplicated.setValue(200);
        assertEquals(200, accountingFlowDuplicated.getValue(), 0.0);
        assertEquals(0, accountingFlow.getValue(), 0.0);
    }

    @Test
    public void comparaFluxoContabil() {
        final AccountingFlow af1 = new AccountingFlow(1);
        final AccountingFlow af2 = new AccountingFlow(2);
        assertEquals(-1, af1.compareTo(af2));
        af2.setValue(1);
        assertEquals(0, af1.compareTo(af2));
    }

    @Test
    public void paraStringFluxoContabilPositivo() {
        accountingFlow = new AccountingFlow(1357.93);
        assertEquals("1.357,93", accountingFlow.toString());
    }

    @Test
    public void paraStringFluxoContabilNegativo() {
        accountingFlow = new AccountingFlow(-1357.93);
        assertEquals("-1.357,93", accountingFlow.toString());
    }

}
