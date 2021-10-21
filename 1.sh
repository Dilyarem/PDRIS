dir=$(echo $1 | grep -o '[^\/]*' | tail -1)
rm -f -r $dir
git clone $1
cd $dir
git diff --name-only $2..$3 > "../diff.txt"
