package org.testunited.core.data;

import org.springframework.data.repository.CrudRepository;
import org.testunited.core.TestTarget;

public interface TestTargetRepository extends CrudRepository<TestTarget, Long> {

}
