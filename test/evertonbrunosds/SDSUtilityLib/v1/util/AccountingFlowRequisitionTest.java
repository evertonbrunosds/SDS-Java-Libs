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
 * Classe responsável por efetuar testes na requisição de fluxo contábil.
 * @author Everton Bruno Silva dos Santos.
 * @version 1.0
 * @since 1.0
 */
public class AccountingFlowRequisitionTest {
    
    public AccountingFlowRequisitionTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void criandoRequisicaoDeFluxoContabilComValorNegativo() {
        final AccountingFlowRequisition afr = new AccountingFlowRequisition(1, new AccountingFlow(-2.5));
        assertEquals(-2.5, afr.getValue(), 0.0);
    }
    
    @Test
    public void criandoRequisicaoDeFluxoContabilComValorPositivo() {
        final AccountingFlowRequisition afr = new AccountingFlowRequisition(1, new AccountingFlow(2.5));
        assertEquals(2.5, afr.getValue(), 0.0);
    }
    
    @Test
    public void alterandoFluxoContabilParaValorNegativoEmRequisicaoDeFluxoContabil() {
        final AccountingFlowRequisition afr = new AccountingFlowRequisition(1, new AccountingFlow(2.5));
        assertEquals(2.5, afr.getValue(), 0.0);
        afr.getItem().setValue(-2.5);
        assertEquals(-2.5, afr.getValue(), 0.0);
    }
    
    @Test
    public void alterandoFluxoContabilParaValorPositivoEmRequisicaoDeFluxoContabil() {
        final AccountingFlowRequisition afr = new AccountingFlowRequisition(1, new AccountingFlow(-2.5));
        assertEquals(-2.5, afr.getValue(), 0.0);
        afr.getItem().setValue(2.5);
        assertEquals(2.5, afr.getValue(), 0.0);
    }
    
    @Test
    public void verificaIdependênciaDeRequisicaoFluxoContabilDuplicado() {
        final AccountingFlowRequisition afr1 = new AccountingFlowRequisition(1, new AccountingFlow(-2.5));
        final AccountingFlowRequisition afr2 = afr1.duplicate();
        afr2.getItem().setValue(500);
        afr2.setAmount(0);
        assertEquals(1, afr1.getAmount(), 0.0);
        assertEquals(0, afr2.getAmount(), 0.0);
        assertEquals(-2.5, afr1.getValue(), 0.0);
        assertEquals(0, afr2.getValue(), 0.0);
        assertEquals(-2.5, afr1.getItem().getValue(), 0.0);
        assertEquals(500, afr2.getItem().getValue(), 0.0);
    }
    
    @Test
    public void paraStringRequisicaoDeFluxoContabilPositivo() {
        final AccountingFlowRequisition afr = new AccountingFlowRequisition(1, new AccountingFlow(1357.93));
        assertEquals("1.357,93", afr.toString());
    }
    
    @Test
    public void paraStringRequisicaoDeFluxoContabilNegativo() {
        final AccountingFlowRequisition afr = new AccountingFlowRequisition(1, new AccountingFlow(-1357.93));
        assertEquals("-1.357,93", afr.toString());
    }
    
    @Test
    public void mudeParaPositivoQuantidadesNegativas() {
        final AccountingFlowRequisition afr = new AccountingFlowRequisition(-5, new AccountingFlow(-1357.93));
        assertEquals(5, afr.getAmount(), 0.0);
        afr.setAmount(-7);
        assertEquals(7, afr.getAmount(), 0.0);
    }
    
    @Test
    public void quantidadeInterfereNoValorFinalDaRequisicaoDeFluxoContabil() {
        final AccountingFlowRequisition afr = new AccountingFlowRequisition(5, new AccountingFlow(-300.93));
        assertEquals(5, afr.getAmount(), 0.00);
        assertEquals(-300.93, afr.getItem().getValue(), 0.00);
        assertEquals(-1504.65, afr.getValue(), 0.00);
        assertEquals("-1.504,65", afr.toString());
        afr.setAmount(2);
        assertEquals(2, afr.getAmount(), 0.00);
        assertEquals(-300.93, afr.getItem().getValue(), 0.00);
        assertEquals(-601.86, afr.getValue(), 0.00);
        assertEquals("-601,86", afr.toString());
        
    }
    
    @Test
    public void compareNotNullWithNotNull() {
        final AccountingFlowRequisition afr1 = new AccountingFlowRequisition(5, new AccountingFlow(-300.93));
        final AccountingFlowRequisition afr2 = afr1.duplicate();
        assertEquals(0, AccountingFlowRequisition.compare(afr1, afr2));
        afr1.setAmount(7);
        assertEquals(1, AccountingFlowRequisition.compare(afr1, afr2));
        afr2.setAmount(10);
        assertEquals(-1, AccountingFlowRequisition.compare(afr1, afr2));
    }

    @Test
    public void compareNullWithNull() {
        assertEquals(0, AccountingFlowRequisition.compare(null, null));
    }

    @Test
    public void compareNotNullWithNull() {
        final AccountingFlowRequisition afr = new AccountingFlowRequisition(5, new AccountingFlow(-300.93));
        assertEquals(1, AccountingFlowRequisition.compare(afr, null));
    }

    @Test
    public void compareNullWithNotNull() {
        final AccountingFlowRequisition afr = new AccountingFlowRequisition(5, new AccountingFlow(-300.93));
        assertEquals(-1, AccountingFlowRequisition.compare(null, afr));
    }

    @Test
    public void compareToNotNull() {
        final AccountingFlowRequisition afr1 = new AccountingFlowRequisition(2, new AccountingFlow(3));
        AccountingFlowRequisition afr2 = afr1.duplicate();
        assertEquals(0, afr1.compareTo(afr2));
        afr1.setAmount(5);
        assertEquals(1, afr1.compareTo(afr2));
        afr2.setAmount(10);
        assertEquals(-1, afr1.compareTo(afr2));
        afr2 = afr1.duplicate();
        assertEquals(0, afr1.compareTo(afr2));
        afr1.getItem().setValue(5);
        assertEquals(1, afr1.compareTo(afr2));
        afr2.getItem().setValue(7);
        assertEquals(-1, afr1.compareTo(afr2));
    }

    @Test
    public void compareToNull() {
        final AccountingFlowRequisition afr = new AccountingFlowRequisition(5, new AccountingFlow(-300.93));
        assertEquals(1, afr.compareTo(null));
    }
}
