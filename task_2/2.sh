case $1 in
  "START")
  	./monitor.sh &
  	echo "Running monitoring. PID: $!"
  	;;
  "STATUS")
	PID=$(echo $(ps -q $2) | awk '{print $5}')
	if [[ $PID > 0 ]]; then 
		echo "Process is running"
		echo $PID $2
	else
		echo "Process is NOT running"
	fi
	;;
  "STOP")
	kill $2
	;;

esac
