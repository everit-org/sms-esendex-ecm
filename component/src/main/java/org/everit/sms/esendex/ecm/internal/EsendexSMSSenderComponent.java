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
package org.everit.sms.esendex.ecm.internal;

import aQute.bnd.annotation.headers.ProvideCapability;

import org.everit.osgi.ecm.annotation.Activate;
import org.everit.osgi.ecm.annotation.AttributeOrder;
import org.everit.osgi.ecm.annotation.Component;
import org.everit.osgi.ecm.annotation.ConfigurationPolicy;
import org.everit.osgi.ecm.annotation.Service;
import org.everit.osgi.ecm.annotation.attribute.StringAttribute;
import org.everit.osgi.ecm.annotation.attribute.StringAttributes;
import org.everit.osgi.ecm.extender.ECMExtenderConstants;
import org.everit.sms.SMSSender;
import org.everit.sms.esendex.EsendexSMSSender;
import org.everit.sms.esendex.ecm.EsendexSMSSenderComponentConfig;

/**
 * Esendex SMS sender component.
 */
@Component(
    componentId = EsendexSMSSenderComponentConfig.COMPONENT_ID,
    label = "Everit - Esendex SMS Sender Component",
    description = "SMS Sender Compnent using the http://developers.esendex.com/ Java SDK",
    configurationPolicy = ConfigurationPolicy.FACTORY)
@ProvideCapability(
    ns = ECMExtenderConstants.CAPABILITY_NS_COMPONENT,
    value = ECMExtenderConstants.CAPABILITY_ATTR_CLASS + "=${@class}")
@AttributeOrder({
    EsendexSMSSenderComponentConfig.ATTR_USERNAME,
    EsendexSMSSenderComponentConfig.ATTR_PASSWORD,
    EsendexSMSSenderComponentConfig.ATTR_ACCOUNT_REFERENCE,
    EsendexSMSSenderComponentConfig.ATTR_SMS_PROVIDER })
@StringAttributes({
    @StringAttribute(
        attributeId = EsendexSMSSenderComponentConfig.ATTR_SMS_PROVIDER,
        defaultValue = EsendexSMSSenderComponentConfig.SMS_PROVIDER_ESENDEX,
        label = "Provider",
        description = "The provider of the implementation.") })
@Service(value = { SMSSender.class })
public class EsendexSMSSenderComponent implements SMSSender {

  private String accoutReference;

  private String password;

  private SMSSender smsSender;

  private String username;

  @Activate
  public void activate() {
    smsSender = new EsendexSMSSender(username, password, accoutReference);
  }

  @Override
  public void sendSMS(final String recipientNumber, final String message) {
    smsSender.sendSMS(recipientNumber, message);
  }

  @StringAttribute(
      attributeId = EsendexSMSSenderComponentConfig.ATTR_ACCOUNT_REFERENCE,
      defaultValue = "",
      label = "Account reference",
      description = "The Esendex account reference.")
  public void setAccoutReference(final String accoutReference) {
    this.accoutReference = accoutReference;
  }

  @StringAttribute(
      attributeId = EsendexSMSSenderComponentConfig.ATTR_PASSWORD,
      defaultValue = "",
      label = "Password",
      description = "The password belonging to the username of the Esendex account.")
  public void setPassword(final String password) {
    this.password = password;
  }

  @StringAttribute(
      attributeId = EsendexSMSSenderComponentConfig.ATTR_USERNAME,
      defaultValue = "",
      label = "Username",
      description = "The username of the Exendex account.")
  public void setUsername(final String username) {
    this.username = username;
  }

}
