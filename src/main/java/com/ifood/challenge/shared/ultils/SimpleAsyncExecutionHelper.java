package com.ifood.challenge.shared.ultils;

import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.function.Supplier;
import org.slf4j.Logger;

/**
 * The class Simple async execution helper.
 * Created at 24 de jan de 2020 23:00:56
 */
public class SimpleAsyncExecutionHelper {

  private SimpleAsyncExecutionHelper() {
  }

  /**
   * Execute async t.
   * Created at 24 de jan de 2020 23:00:56
   *
   * @param <T>      the type parameter
   * @param executor the executor
   * @param log      the log
   * @return the t
   */
  public static <T extends Object> T executeAsync(Supplier<T> executor, Logger log) {
    try {
      final CompletableFuture<T> spotifyDtoFuture =
          CompletableFuture.supplyAsync(executor);
      // Thread free to do other tasks...
      final T objectToBeReturned = spotifyDtoFuture.join();
      return objectToBeReturned;
    } catch (CompletionException e) {
      log.error("Error: {}", e.getCause().getMessage());
    } catch (CancellationException e) {
      log.error("Async operation cancelled.");
    }

    return null;
  }
}
