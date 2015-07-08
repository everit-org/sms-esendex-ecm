/*
 * Copyright (C) 2011 Everit Kft. (http://www.everit.org)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.everit.sms.esendex.ecm.tests;

import aQute.bnd.annotation.headers.ProvideCapability;

import org.everit.osgi.ecm.annotation.Component;
import org.everit.osgi.ecm.annotation.Service;
import org.everit.osgi.ecm.annotation.ServiceRef;
import org.everit.osgi.ecm.annotation.attribute.StringAttribute;
import org.everit.osgi.ecm.annotation.attribute.StringAttributes;
import org.everit.osgi.ecm.extender.ECMExtenderConstants;
import org.everit.sms.SMSSender;
import org.everit.sms.esendex.ecm.EsendexSMSSenderComponentConfig;
import org.junit.Assert;
import org.junit.Test;

/**
 * Test Esendex ECM SMS Sender.
 */
@Component
@ProvideCapability(ns = ECMExtenderConstants.CAPABILITY_NS_COMPONENT,
    value = ECMExtenderConstants.CAPABILITY_ATTR_CLASS + "=${@class}")
@StringAttributes({
    @StringAttribute(attributeId = "eosgi.testId", defaultValue = "EsendexSMSSenderTest"),
    @StringAttribute(attributeId = "eosgi.testEngine", defaultValue = "junit4") })
@Service
public class EsendexSMSSenderTestComponent {

  private SMSSender smsSender;

  @ServiceRef(defaultValue = "(" + EsendexSMSSenderComponentConfig.ATTR_SMS_PROVIDER + "="
      + EsendexSMSSenderComponentConfig.SMS_PROVIDER_ESENDEX + ")")
  public void setSmsSender(final SMSSender smsSender) {
    this.smsSender = smsSender;
  }

  @Test
  public void testSMSSending() {
    Assert.assertTrue(smsSender != null);
  }

}
