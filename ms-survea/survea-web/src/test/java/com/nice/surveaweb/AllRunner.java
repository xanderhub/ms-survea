package com.nice.surveaweb;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses(value = {AcceptanceTests.class})
public class AllRunner {
}
