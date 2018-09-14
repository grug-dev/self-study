package com.kkpa.java8.lambda;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class InvoiceService {

  public List<Invoice> filterInvoice(List<Invoice> invoices, Predicate<Invoice> invoicePredicate) {
    Supplier<List<Invoice>> invoicesSupplier = ArrayList::new;
    List<Invoice> invoiceFiltered = invoicesSupplier.get();

    invoices.stream().forEach(inv -> {
      if (invoicePredicate.test(inv)) {
        invoiceFiltered.add(inv);
      }
    });

    return invoiceFiltered;

  }

  public List<String> extractAmountsUsingReferenceMethodFrom(List<Invoice> invoices) {
    return invoices.stream().map(inv -> inv.getAmount()).map(String::valueOf)
        .collect(Collectors.toList());
  }

  public List<Invoice> sortByAmountDesc(List<Invoice> invoices) {

    Comparator<Invoice> amountComparator = Comparator.comparing((inv) -> inv.getAmount() * -1);

    invoices.sort(amountComparator);

    return invoices;
  }


}
