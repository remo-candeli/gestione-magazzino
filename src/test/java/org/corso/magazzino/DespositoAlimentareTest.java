package org.corso.magazzino;

import junit.framework.TestCase;
import org.corso.magazzino.exceptions.ErroreCapacitaMassimaDepositoSuperata;
import org.corso.magazzino.exceptions.ErroreCaricoException;
import org.junit.Before;
import org.junit.Test;


import java.time.LocalDate;

import static junit.framework.TestCase.assertEquals;


public class DespositoAlimentareTest {

    Prodotto prodottoAlimentareDaCaricare;
    private DepositoAlimentare depositoAlimentari;


    @Before
    public void setUp() {
        //DepositoAlimentare depositoAlimentari = Mockito.mock(DepositoAlimentare.class);
        depositoAlimentari = new DepositoAlimentare(Magazzino.DEPOSITO_ALIMENTARI, 20);
        prodottoAlimentareDaCaricare = new ProdottoAlimentare("Spaghetti"
                , "Barilla"
                , 100
                , LocalDate.of(2021, 04, 28));
    }

    @Test(expected = ErroreCaricoException.class)
    public void erroreSeQuantitaNegativa_caricoDeposito() throws ErroreCaricoException, ErroreCapacitaMassimaDepositoSuperata {
        depositoAlimentari.caricoDeposito(prodottoAlimentareDaCaricare, -5);
    }

    @Test(expected = ErroreCaricoException.class)
    public void erroreSeProdottoNullo_caricoDeposito() throws ErroreCaricoException, ErroreCapacitaMassimaDepositoSuperata {
        depositoAlimentari.caricoDeposito(null, 5);
    }

    @Test(expected = ErroreCapacitaMassimaDepositoSuperata.class)
    public void verificaSeCapacitaSuperata_caricoDeposito() throws ErroreCaricoException, ErroreCapacitaMassimaDepositoSuperata {
        depositoAlimentari.caricoDeposito(prodottoAlimentareDaCaricare, 21);
    }
}
