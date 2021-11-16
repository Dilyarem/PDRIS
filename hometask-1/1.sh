#!/bin/bash
dir="$(dirname $0)/$(echo $1 | grep -o '[^\/]*' | tail -1)"
rm -f -r $dir
git clone $1 $dir
cd $dir
git diff --name-only $2..$3 > "../diff.txt"
if ! [  $? -eq 0 ]; then
	echo "NO SUCH BRANCHES"
fi
