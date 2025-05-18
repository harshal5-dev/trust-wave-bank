package com.trustwave.cards.audit;

import org.springframework.data.domain.AuditorAware;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("auditorAwareImpl")
public class AuditAwareImpl implements AuditorAware<String> {

  /**
   * This method is used to get the current auditor.
   * In this case, it returns an empty Optional, which means no auditor is set.
   *
   * @return an empty Optional
   */
  @Override
  @NonNull
  public Optional<String> getCurrentAuditor() {
    return Optional.of("CARDS_MS");
  }
}
