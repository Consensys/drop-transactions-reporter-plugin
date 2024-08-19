// Copyright 2024, Consensys Software Inc.
// SPDX-License-Identifier: Apache-2.0
package net.consensys.linea;

import static net.consensys.linea.DropTransactionsReporterCliOptions.NAMESPACE;

import com.google.auto.service.AutoService;
import java.util.Optional;
import org.hyperledger.besu.datatypes.Transaction;
import org.hyperledger.besu.plugin.BesuContext;
import org.hyperledger.besu.plugin.BesuPlugin;
import org.hyperledger.besu.plugin.services.BesuEvents;
import org.hyperledger.besu.plugin.services.PicoCLIOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A Besu plugin that listens for drop transactions events and reports them to a configured
 * endpoint.
 */
@AutoService(BesuPlugin.class)
public class DropTransactionReporterPlugin implements BesuPlugin {
  private static final Logger LOG = LoggerFactory.getLogger(DropTransactionReporterPlugin.class);
  private BesuContext besuContext;
  private Optional<Long> subscriptionId = Optional.empty();
  private final DropTransactionsReporterCliOptions cliParams =
      new DropTransactionsReporterCliOptions();

  @Override
  public void register(final BesuContext besuContext) {
    LOG.trace("Registering plugin ...");
    this.besuContext = besuContext;
    registerCliOptions(besuContext);
  }

  @Override
  public void start() {
    LOG.trace("Starting plugin ...");
    subscriptionId =
        besuContext
            .getService(BesuEvents.class)
            .map(events -> events.addTransactionDroppedListener(this::onTransactionDropped));
    LOG.trace("Transaction Drop Listener with ID#{} registered.", subscriptionId);
  }

  private void onTransactionDropped(Transaction transaction) {
    LOG.trace(
        "Transaction dropped: {}. Reporting to: {}",
        transaction.getHash(),
        cliParams.getEndpoint());
    // TODO: Implement the logic to report the transaction to a configured endpoint
  }

  @Override
  public void stop() {
    LOG.trace("Stopping plugin ...");
    subscriptionId.ifPresent(
        id ->
            besuContext
                .getService(BesuEvents.class)
                .ifPresent(besuEvents -> besuEvents.removeTransactionDroppedListener(id)));
    LOG.trace("Transaction dropped listener with ID#{} removed.", subscriptionId);
  }

  private void registerCliOptions(final BesuContext besuContext) {
    besuContext
        .getService(PicoCLIOptions.class)
        .orElseThrow(() -> new IllegalStateException("Expecting PicoCLIOptions to be present"))
        .addPicoCLIOptions(NAMESPACE, cliParams);
  }
}
