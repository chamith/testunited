#! /bin/bash
data_dir="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"


echo ""
echo "posting test targets"
curl -H "Content-Type:application/json" -d @$data_dir/testtargets.json localhost:8080/testtargets/bulk
echo ""
echo "posting test groups"
curl -H "Content-Type:application/json" -d @$data_dir/testgroups.json localhost:8080/testgroups/bulk
echo ""
echo "posting test cases"
curl -H "Content-Type:application/json" -d @$data_dir/testcases.json localhost:8080/testcases/bulk
echo ""
