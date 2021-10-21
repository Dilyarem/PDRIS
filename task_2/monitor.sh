FORMAT="timestamp;all_memory;free_memory;%usaged"

get_info(){
	TIMESTAMP=$(date +"%T")
	ALL_MEMORY=$(echo $(free -m) | awk '{print $8}')
	FREE_MEMORY=$(echo $(free -m) | awk '{print $10}')
	USAGED=$(echo $(free -m) | awk '{print $9}')
	USAGED=$(awk "BEGIN {print "$USAGED/$ALL_MEMORY*100"}")
	echo "$TIMESTAMP;  $ALL_MEMORY;  $FREE_MEMORY;  $USAGED"
}

echo $FORMAT >> memory.csv
while true
do
	echo $(get_info) >> memory.csv
	sleep 10m
done
