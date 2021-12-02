#!/usr/bin/env python3

import urllib.request
import argparse, sys

parser=argparse.ArgumentParser()
parser.add_argument('--outerServer')
args=parser.parse_args()

fp = urllib.request.urlopen("http://{}:1234/".format(args.outerServer))

encodedContent = fp.read()
decodedContent = encodedContent.decode("utf8")
print(decodedContent)

fp.close()
