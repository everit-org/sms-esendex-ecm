package org.everit.sms.esendex.ecm;

import org.everit.sms.api.SMSSender;
import org.junit.Assert;
import org.junit.Test;

public class EsendexSMSSenderComponentTest {

  private SMSSender createComponent() throws Exception {
    EsendexSMSSenderComponent component = new EsendexSMSSenderComponent();
    component.setUsername("fakeEmail@asdf.qwer");
    component.setPassword("fakePass");
    component.setAccoutReference("fakeRef");
    component.activate();
    return component;
  }

  @Test
  public void testSMSSending() throws Exception {
    SMSSender smsSender = createComponent();
    try {
      smsSender.sendSMS("fakeNumber", "message");
      Assert.fail("fake credentials should throw exception");
    } catch (Exception e) {
      Assert.assertTrue(e.getMessage() != null);
    }
  }

}
