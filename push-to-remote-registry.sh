#! /bin/bash
images=(core launcher)
project_prefix=testunited
group_prefix=testunited
host_prefix=$1
for i in "${images[@]}"; 
do
	image_local=$group_prefix"/"$project_prefix"-"$i
	image_remote=$host_prefix"/"$group_prefix"/"$project_prefix"-"$i
	echo $image_local ">" $image_remote
	docker tag $image_local $image_remote
	docker push $image_remote
	echo "---------------------"
done