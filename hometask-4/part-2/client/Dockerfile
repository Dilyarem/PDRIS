FROM python:latest

ADD client.py /client/

WORKDIR /client/ 

ENTRYPOINT ["python3", "client.py"]
CMD ["--outerServer=server"]
