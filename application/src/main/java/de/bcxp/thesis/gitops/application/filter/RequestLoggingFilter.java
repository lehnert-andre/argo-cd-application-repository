package de.bcxp.thesis.gitops.application.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Andre Lehnert, BettercallPaul gmbh
 */
@Order(1)
public class RequestLoggingFilter implements Filter {

  private static final Logger LOG = LoggerFactory.getLogger(RequestLoggingFilter.class);

  @Override
  public void init(final FilterConfig filterConfig) throws ServletException {
    LOG.info("Initializing filter: {}", getClass().getCanonicalName());
  }

  @Override
  public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain)
      throws IOException, ServletException {
    final HttpServletRequest req = (HttpServletRequest) request;
    final HttpServletResponse res = (HttpServletResponse) response;

    LOG.debug("Receive {}: {}", req.getMethod(), req.getRequestURI());

    chain.doFilter(request, response);

    LOG.debug("Send response for '{}: {}' with status code '{}'",
        req.getMethod(), req.getRequestURI(), res.getStatus());
  }

  @Override
  public void destroy() {
    LOG.warn("Destructing filter: {}", getClass().getCanonicalName());
  }
}
