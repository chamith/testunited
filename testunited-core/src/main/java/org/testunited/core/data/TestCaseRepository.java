package org.testunited.core.data;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.testunited.core.*;

public interface TestCaseRepository extends CrudRepository<TestCase, UUID> {

	public List<TestCase> findByTestTargetId(long testTargetId);
	public List<TestCase> findByTestTargetIdAndTestGroupId(long testTargetId, long testGroupId);

}
