// Copyright 2024, Consensys Software Inc.
// SPDX-License-Identifier: Apache-2.0
package net.consensys.linea;

import java.net.URI;
import picocli.CommandLine;

public class DropTransactionsReporterCliOptions {
  static final String NAMESPACE = "drop-transaction-reporter";

  @CommandLine.Option(
      names = "--plugin-" + NAMESPACE + "-endpoint",
      description = "URI to report drop transactions",
      required = true,
      paramLabel = "<URI>")
  private URI endpoint = URI.create("http://localhost:8080");

  public URI getEndpoint() {
    return endpoint;
  }

  public void setEndpoint(final URI endpoint) {
    this.endpoint = endpoint;
  }

  public DropTransactionsReporterCliOptions() {}
}
