package org.testunited.core.data;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.testunited.core.*;

public interface TestGroupRepository extends CrudRepository<TestGroup, UUID> {

}
