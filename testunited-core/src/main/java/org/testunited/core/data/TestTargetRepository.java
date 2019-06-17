package org.testunited.core.data;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.testunited.core.TestTarget;

public interface TestTargetRepository extends CrudRepository<TestTarget, UUID> {

}
