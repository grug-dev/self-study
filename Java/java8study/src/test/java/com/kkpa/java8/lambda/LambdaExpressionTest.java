package com.kkpa.java8.lambda;

import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


import static org.hamcrest.Matchers.*;
import org.junit.Before;
import org.junit.Test;

public class LambdaExpressionTest {

    private InvoiceService invoiceService;
    private List<Invoice> invoices = new ArrayList<>();
    private final static int MAX_INVOICES = 10;
    private final static int MULTIPLE_AMOUNT_BY_EACH_INVOICE = 100;

    @Before
    public void setup() {
	invoiceService = new InvoiceService();
	fillInvoicesData();
    }
    
    private void fillInvoicesData() {
	invoices.clear();
	IntStream.rangeClosed(1, MAX_INVOICES).forEach( index -> {
	    invoices.add(new Invoice("Invoice_" + index, index*MULTIPLE_AMOUNT_BY_EACH_INVOICE));
	});
    }
    
    @Test
    public final void filterInvoiceGreaterThanAmount() {
	int HALF_INVOICES = MAX_INVOICES / 2 ;
	
	List<Invoice> invoicesFiltered = invoiceService.filterInvoice(invoices, (inv) -> {
	    return inv.getAmount() > HALF_INVOICES * MULTIPLE_AMOUNT_BY_EACH_INVOICE;
	});
	
	assertThat(invoicesFiltered, hasSize(HALF_INVOICES));
    }
    
    @Test
    public void extractAmountsAsStrings() {
	List<String> amounts;
	
	amounts = invoiceService.extractAmountsUsingReferenceMethodFrom(invoices);
	
	assertThat(amounts, hasSize(MAX_INVOICES));
	assertThat(amounts, isA(List.class));
    }
    
    @Test
    public void sortByAmountDesc() {
	List<Invoice> result = invoiceService.sortByAmountDesc(invoices);
	
	List<Integer> amounts = result.stream().map( inv -> inv.getAmount()).collect(Collectors.toList());
	
	assertThat(amounts, contains(1000,900,800,700,600,500,400,300,200,100) );
    }

}
