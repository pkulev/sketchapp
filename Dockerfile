FROM clojure:openjdk-11-lein

WORKDIR /code

COPY ./project.clj ./
COPY ./src/ ./src/

RUN lein deps
RUN lein install

EXPOSE 10000

CMD ["lein", "run"]
