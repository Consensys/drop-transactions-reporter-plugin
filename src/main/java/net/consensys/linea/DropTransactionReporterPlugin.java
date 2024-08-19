// Copyright 2024, Consensys Software Inc.
// SPDX-License-Identifier: Apache-2.0
package net.consensys.linea;

import com.google.auto.service.AutoService;
import org.hyperledger.besu.plugin.BesuContext;
import org.hyperledger.besu.plugin.BesuPlugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A Besu plugin that listens for drop transactions events and reports them to a configured
 * endpoint.
 */
@AutoService(BesuPlugin.class)
public class DropTransactionReporterPlugin implements BesuPlugin {
  private static final Logger LOG = LoggerFactory.getLogger(DropTransactionReporterPlugin.class);

  @Override
  public void register(final BesuContext besuContext) {
    LOG.trace("Registering plugin ...");
  }

  @Override
  public void start() {
    LOG.trace("Starting plugin ...");
  }

  @Override
  public void stop() {
    LOG.trace("Stopping plugin ...");
  }
}
