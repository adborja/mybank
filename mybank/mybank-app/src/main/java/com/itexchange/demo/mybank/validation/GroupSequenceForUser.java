package com.itexchange.demo.mybank.validation;

import javax.validation.GroupSequence;
import javax.validation.groups.Default;

@GroupSequence({ Default.class, GroupCustomerNames.class, GroupCustomerContactInfo.class })
public interface GroupSequenceForUser {
}