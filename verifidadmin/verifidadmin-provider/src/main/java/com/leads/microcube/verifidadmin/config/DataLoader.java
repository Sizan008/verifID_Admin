//package com.leads.microcube.verifidadmin.config;
//
//import static com.leads.microcube.verifidadmin.transaction.TransactionState.RECEIVED;
//
//import com.leads.microcube.verifidadmin.transaction.TransactionQueryService;
//import com.leads.microcube.verifidadmin.transaction.TransactionService;
//import com.leads.microcube.verifidadmin.transaction.command.RegisterTransaction;
//import com.leads.microcube.verifidadmin.transaction.query.InwardTransaction;
//import java.time.LocalDate;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//@Component
//public class DataLoader implements CommandLineRunner {
//  private static final Logger logger = LoggerFactory.getLogger(DataLoader.class.getName());
//
//  private final TransactionService transactionService;
//  private final TransactionQueryService transactionQueryService;
//  private final boolean mockEnabled;
//  private final Long mockData;
//
//  public DataLoader(
//          TransactionService transactionService,
//          TransactionQueryService transactionQueryService,
//          @Value("${microcube.mock.enabled}") boolean mockEnabled,
//          @Value("${microcube.mock.data}") Long mockData) {
//    this.transactionService = transactionService;
//    this.transactionQueryService = transactionQueryService;
//    this.mockEnabled = mockEnabled;
//    this.mockData = mockData;
//  }
//
//  @Override
//  public void run(String... args) {
//    if (!mockEnabled) {
//      return;
//    }
//
//    try {
//      loadTransactions();
//    } catch (Exception e) {
//      logger.error(e.getMessage());
//    }
//  }
//
//  private void loadTransactions() {
//    RegisterTransaction command;
//    for (int i = 1; i <= this.mockData; i++) {
//      command = new RegisterTransaction();
//      command.setTransactionDate(LocalDate.now());
//      String accountNumber = "100" + String.format("%04d", i);
//      command.setAccountNumber(accountNumber);
//      command.setInstrumentDate(LocalDate.now());
//      String instrumentNumber = "999" + String.format("%04d", i);
//      command.setInstrumentNumber(instrumentNumber);
//      command.setChannel("BACH");
//      command.setNarration("Transaction receive from BACH");
//      command.setState(RECEIVED);
//      double amount = 100000 * i;
//      command.setAmount(amount);
//
//      if (i % 2 == 0) {
//        if (i % 3 == 0) {
//          command.setAccountNumber(command.getAccountNumber().replace("1", "2"));
//        } else {
//          command.setAmount(command.getAmount() + i * 1000);
//        }
//      }
//
//      try {
//        transactionService.process(command);
//      } catch (Exception e) {
//        logger.error(e.getMessage());
//      }
//    }
//    // find book by ID
//    InwardTransaction txn = transactionQueryService.retrieveTransaction(1L);
//    logger.info("Retrieve transaction byId(1L):");
//    logger.info("--------------------------------");
//    logger.info(txn.getNarration());
//  }
//}
